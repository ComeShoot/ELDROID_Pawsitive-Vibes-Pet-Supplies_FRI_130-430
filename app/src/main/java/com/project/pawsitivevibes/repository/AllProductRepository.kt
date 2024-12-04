package com.project.pawsitivevibes.repository

import com.project.pawsitivevibes.model.AddToCartRequest
import com.project.pawsitivevibes.model.AllProduct
import com.project.pawsitivevibes.model.ApiResponse
import com.project.pawsitivevibes.model.CartItemResponse
import com.project.pawsitivevibes.model.CartItemsRequest
import com.project.pawsitivevibes.model.CartItemsResponse
import com.project.pawsitivevibes.network.ApiService
import com.project.pawsitivevibes.network.RetrofitBuilder
import retrofit2.Response

class AllProductRepository {
    private val apiService: ApiService by lazy {
        RetrofitBuilder.getInstance().create(ApiService::class.java)
    }

    suspend fun getAllProducts(): List<AllProduct> {
        return apiService.getAllProducts()
    }

    suspend fun addToCart(request: AddToCartRequest): Response<CartItemResponse> {
        return apiService.addToCart(request)
    }

    suspend fun getCartItems(request: CartItemsRequest): Response<CartItemsResponse> {
        return apiService.getCartItems(request)
    }

}

