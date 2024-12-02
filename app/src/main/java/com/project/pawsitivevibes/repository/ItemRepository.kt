package com.project.pawsitivevibes.repository

import com.project.pawsitivevibes.model.Product
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
}
