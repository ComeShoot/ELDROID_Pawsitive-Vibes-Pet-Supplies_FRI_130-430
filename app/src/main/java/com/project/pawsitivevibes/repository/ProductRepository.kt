package com.project.pawsitivevibes.repository

import com.project.pawsitivevibes.model.Product
import com.project.pawsitivevibes.network.ApiService
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Response
import java.io.File

class ProductRepository(private val apiService: ApiService) {

    suspend fun uploadProduct(
        name: String,
        description: String,
        price: Double,
        quantity: Int,
        imageFile: File,
        authToken: String // Add token parameter
    ): Response<Product> {
        val requestBodyName = name.toRequestBody("text/plain".toMediaTypeOrNull())
        val requestBodyDescription = description.toRequestBody("text/plain".toMediaTypeOrNull())
        val requestBodyPrice = price.toString().toRequestBody("text/plain".toMediaTypeOrNull())
        val requestBodyQuantity = quantity.toString().toRequestBody("text/plain".toMediaTypeOrNull())

        val requestFile = imageFile.asRequestBody("image/*".toMediaTypeOrNull())
        val imagePart = MultipartBody.Part.createFormData("prod_image", imageFile.name, requestFile)

        return apiService.uploadProduct(
            authToken, // Pass the token to the API service
            requestBodyName,
            requestBodyDescription,
            requestBodyPrice,
            requestBodyQuantity,
            imagePart
        )
    }

}
