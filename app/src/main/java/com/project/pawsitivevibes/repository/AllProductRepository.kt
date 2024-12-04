package com.project.pawsitivevibes.repository

import com.project.pawsitivevibes.model.AllProduct
import com.project.pawsitivevibes.network.ApiService
import com.project.pawsitivevibes.network.RetrofitBuilder

class AllProductRepository {
    private val apiService: ApiService by lazy {
        RetrofitBuilder.getInstance().create(ApiService::class.java)
    }

    suspend fun getAllProducts(): List<AllProduct> {
        return apiService.getAllProducts()
    }
}
