package com.project.pawsitivevibes.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.pawsitivevibes.model.AllProduct
import com.project.pawsitivevibes.repository.AllProductRepository
import kotlinx.coroutines.launch

class AllProductViewModel : ViewModel() {
    private val repository = AllProductRepository()
    private val _products = MutableLiveData<List<AllProduct>>()
    val products: LiveData<List<AllProduct>> get() = _products

    init {
        fetchAllProducts()
    }

    private fun fetchAllProducts() {
        viewModelScope.launch {
            try {
                val productList = repository.getAllProducts()
                Log.d("ProductViewModel", "Fetched products: $productList")
                _products.postValue(productList)
            } catch (e: Exception) {
                Log.e("ProductViewModel", "Error fetching products", e)
            }
        }
    }

}
