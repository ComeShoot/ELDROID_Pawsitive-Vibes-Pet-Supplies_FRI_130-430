package com.project.pawsitivevibes.repository

import com.project.pawsitivevibes.model.CartResponse
import com.project.pawsitivevibes.network.ApiService
import com.project.pawsitivevibes.network.RetrofitBuilder
import retrofit2.Response

class CartRepository {

    private val apiService: ApiService by lazy {
        RetrofitBuilder.getInstance().create(ApiService::class.java)
    }


}
