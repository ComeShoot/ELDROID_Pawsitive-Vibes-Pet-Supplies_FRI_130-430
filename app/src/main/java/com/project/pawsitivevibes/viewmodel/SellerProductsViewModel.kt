package com.project.pawsitivevibes.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.pawsitivevibes.model.Product
import com.project.pawsitivevibes.model.UpdateProductRequest
import com.project.pawsitivevibes.repository.ItemRepository
import kotlinx.coroutines.launch

class SellerProductsViewModel(application: Application, private val repository: ItemRepository) :
    AndroidViewModel(application) {

    private val _updateResult = MutableLiveData<Boolean>()
    val updateResult: LiveData<Boolean> get() = _updateResult

    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> get() = _products

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun loadProducts(authToken: String, sellerId: String) {
        viewModelScope.launch {
            try {
                val productsList = repository.fetchProductsBySeller(authToken, sellerId)
                _products.postValue(productsList)
            } catch (e: Exception) {
                _error.postValue("Error loading products: ${e.message}")
            }
        }
    }

    private fun getAuthToken(): String? {
        val sharedPreferences = getApplication<Application>().getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)
        return sharedPreferences.getString("auth_token", null)
    }

    fun updateProduct(productId: Int, name: String, description: String, price: Double, quantity: Int) {
        viewModelScope.launch {
            try {
                val authToken = getAuthToken()
                if (authToken == null) {
                    _updateResult.postValue(false)
                    _error.postValue("Authentication token is missing")
                    return@launch
                }

                val updatedProduct = UpdateProductRequest(
                    name = name,
                    description = description,
                    price = price,
                    quantity = quantity
                )

                repository.updateProduct(authToken, productId, updatedProduct)
                _updateResult.postValue(true)
            } catch (e: Exception) {
                _updateResult.postValue(false)
                _error.postValue("Error updating product: ${e.message}")
            }
        }
    }

}

