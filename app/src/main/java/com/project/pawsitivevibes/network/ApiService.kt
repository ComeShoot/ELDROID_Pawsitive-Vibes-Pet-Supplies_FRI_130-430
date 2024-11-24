package com.project.pawsitivevibes.network

import com.project.pawsitivevibes.model.User
import com.project.pawsitivevibes.model.UserLogin
import com.project.pawsitivevibes.repository.LoginResponse
import com.project.pawsitivevibes.repository.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {

    @POST("api/customer/register")
    @Headers("Accept: application/json")
    suspend fun registerUser(@Body user: User): Response<RegisterResponse>

    @POST("api/customer/login")
    @Headers("Accept: application/json")
    suspend fun loginUser(@Body loginUser: UserLogin): Response<LoginResponse>


    companion object {
        fun create(): ApiService {
            return RetrofitBuilder.getInstance().create(ApiService::class.java)
        }
    }
}
