package com.project.pawsitivevibes.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.pawsitivevibes.model.Product
import com.project.pawsitivevibes.repository.ItemRepository
import kotlinx.coroutines.launch

class SellerProductsViewModel(private val repository: ItemRepository) : ViewModel() {

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
}
