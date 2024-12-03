package com.project.pawsitivevibes.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.pawsitivevibes.model.Product
import com.project.pawsitivevibes.repository.ProductRepository
import kotlinx.coroutines.launch
import java.io.File

class AddProductViewModel(private val repository: ProductRepository) : ViewModel() {

    private val _uploadResult = MutableLiveData<Result<Product>>()
    val uploadResult: LiveData<Result<Product>> = _uploadResult

    private val _quantity = MutableLiveData(1) // Default quantity is 1
    val quantity: LiveData<Int> = _quantity

    fun incrementQuantity() {
        val currentQuantity = _quantity.value ?: 1
        _quantity.value = currentQuantity + 1
    }

    fun decrementQuantity() {
        val currentQuantity = _quantity.value ?: 1
        if (currentQuantity > 1) {
            _quantity.value = currentQuantity - 1
        }
    }

    fun uploadProduct(
        name: String,
        description: String,
        price: Double,
        imageFile: File,
        authToken: String
    ) {
        val productQuantity = _quantity.value ?: 1
        viewModelScope.launch {
            try {
                val response = repository.uploadProduct(
                    name,
                    description,
                    price,
                    productQuantity,
                    imageFile,
                    authToken
                )
                if (response.isSuccessful) {
                    _uploadResult.postValue(Result.success(response.body()!!))
                } else {
                    _uploadResult.postValue(Result.failure(Exception("Upload failed")))
                }
            } catch (e: Exception) {
                _uploadResult.postValue(Result.failure(e))
            }
        }
    }
}
