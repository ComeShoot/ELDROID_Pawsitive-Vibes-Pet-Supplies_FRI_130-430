package com.project.pawsitivevibes.network

import com.project.pawsitivevibes.model.ForgotPasswordRequest
import com.project.pawsitivevibes.model.Seller
import com.project.pawsitivevibes.model.User
import com.project.pawsitivevibes.model.UserLogin
import com.project.pawsitivevibes.model.VerifyEmailRequest
import com.project.pawsitivevibes.repository.ForgotPasswordResponse
import com.project.pawsitivevibes.repository.LoginResponse
import com.project.pawsitivevibes.repository.RegisterResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {

    // Customer registration
    @POST("api/customer/register")
    @Headers("Accept: application/json")
    suspend fun registerCustomer(@Body user: User): Response<RegisterResponse>

    // Seller registration
    @POST("api/seller/register")
    @Headers("Accept: application/json")
    suspend fun registerSeller(@Body user: Seller): Response<RegisterResponse>

    @POST("api/customer/login")
    @Headers("Accept: application/json")
    suspend fun loginUser(@Body loginUser: UserLogin): Response<LoginResponse>

    @POST("api/customer/forgot-password")
    @Headers("Accept: application/json")
    suspend fun resetPassword(@Body request: ForgotPasswordRequest): Response<ForgotPasswordResponse>

    @POST("api/customer/verify-email")
    @Headers("Accept: application/json")
    suspend fun verifyEmail(@Body emailRequest: VerifyEmailRequest): Response<ForgotPasswordResponse>

    companion object {
        fun create(): ApiService {
            return RetrofitBuilder.getInstance().create(ApiService::class.java)
        }
    }
}
