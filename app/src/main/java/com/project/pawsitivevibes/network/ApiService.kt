package com.project.pawsitivevibes.network

import com.project.pawsitivevibes.model.AddToCartRequest
import com.project.pawsitivevibes.model.AllProduct
import com.project.pawsitivevibes.model.ApiResponse
import com.project.pawsitivevibes.model.CartItemResponse
import com.project.pawsitivevibes.model.CartItemsRequest
import com.project.pawsitivevibes.model.CartItemsResponse
import com.project.pawsitivevibes.model.CartResponse
import com.project.pawsitivevibes.model.ForgotPasswordRequest
import com.project.pawsitivevibes.model.Product
import com.project.pawsitivevibes.model.Seller
import com.project.pawsitivevibes.model.UpdateProductRequest
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
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

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

    @GET("api/products/seller/{seller_id}")
    @Headers("Accept: application/json")
    suspend fun getProductsBySeller(
        @Header("Authorization") authToken: String,  // Add authorization header
        @Path("seller_id") sellerId: String
    ): Response<List<Product>>

    @PATCH("api/products/update-product/{id}")
    @Headers("Accept: application/json")
    suspend fun updateProduct(
        @Header("Authorization") authToken: String,
        @Path("id") productId: Int,
        @Body updatedProduct: UpdateProductRequest
    ): Response<Product>
    @DELETE("api/products/delete-product/{id}")
    suspend fun deleteProduct(
        @Header("Authorization") authToken: String,
        @Path("id") productId: Int
    ): Response<Unit>

    @GET("api/products/all-products")
    suspend fun getAllProducts(): List<AllProduct>
    @POST("api/cart/add-to-cart")
    suspend fun addToCart(@Body request: AddToCartRequest): Response<CartItemResponse>

    @GET("api/cart/get-cart-items/")
    suspend fun getCartItems(@Query("cust_id") custId: Int): Response<CartItemsResponse>


    companion object {
        fun create(): ApiService {
            return RetrofitBuilder.getInstance().create(ApiService::class.java)
        }
    }
}
