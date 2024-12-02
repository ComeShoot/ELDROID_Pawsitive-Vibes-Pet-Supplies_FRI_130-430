package com.project.pawsitivevibes.network

import com.project.pawsitivevibes.model.ForgotPasswordRequest
import com.project.pawsitivevibes.model.Product
import com.project.pawsitivevibes.model.Seller
import com.project.pawsitivevibes.model.User
import com.project.pawsitivevibes.model.UserLogin
import com.project.pawsitivevibes.model.VerifyEmailRequest
import com.project.pawsitivevibes.repository.ForgotPasswordResponse
import com.project.pawsitivevibes.repository.LoginResponse
import com.project.pawsitivevibes.repository.RegisterResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

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

    @POST("api/products/upload-product")
    @Multipart
    @Headers("Accept: application/json")
    suspend fun uploadProduct(
        @Header("Authorization") authToken: String,  // Add the Authorization header
        @Part("prod_name") prodName: RequestBody,
        @Part("prod_description") prodDescription: RequestBody,
        @Part("prod_price") prodPrice: RequestBody,
        @Part("prod_quantity") prodQuantity: RequestBody,
        @Part prod_image: MultipartBody.Part
    ): Response<Product>



    companion object {
        fun create(): ApiService {
            return RetrofitBuilder.getInstance().create(ApiService::class.java)
        }
    }
}
