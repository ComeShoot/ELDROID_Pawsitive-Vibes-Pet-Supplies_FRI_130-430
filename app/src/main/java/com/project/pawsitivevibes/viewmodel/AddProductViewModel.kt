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

    fun uploadProduct(name: String, description: String, price: Double, quantity: Int, imageFile: File, authToken: String) {
        viewModelScope.launch {
            try {
                val response = repository.uploadProduct(name, description, price, quantity, imageFile, authToken)
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
