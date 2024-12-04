package com.project.pawsitivevibes.repository

import com.project.pawsitivevibes.model.Product
import com.project.pawsitivevibes.model.UpdateProductRequest
import com.project.pawsitivevibes.network.ApiService

class ItemRepository(private val apiService: ApiService) {

    suspend fun fetchProductsBySeller(authToken: String, sellerId: String): List<Product> {
        val response = apiService.getProductsBySeller(authToken, sellerId)
        if (response.isSuccessful) {
            return response.body() ?: emptyList()
        } else {
            throw Exception("Failed to fetch products: ${response.errorBody()?.string()}")
        }
    }

    suspend fun updateProduct(authToken: String, productId: Int, updatedProduct: UpdateProductRequest) {
        val response = apiService.updateProduct(authToken, productId, updatedProduct)
        if (!response.isSuccessful) {
            val errorMessage = response.errorBody()?.string() ?: "Unknown error"
            throw Exception("Failed to update product: $errorMessage")
        }
    }
    suspend fun deleteProduct(authToken: String, productId: Int) {
        val response = apiService.deleteProduct(authToken, productId)
        if (!response.isSuccessful) {
            val errorMessage = response.errorBody()?.string() ?: "Unknown error"
            throw Exception("Failed to delete product: $errorMessage")
        }
    }

}
