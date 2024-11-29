package com.project.pawsitivevibes.repository

import com.project.pawsitivevibes.model.Seller
import com.project.pawsitivevibes.model.User
import com.project.pawsitivevibes.model.UserLogin
import com.project.pawsitivevibes.network.ApiService
import retrofit2.Response

class UserRepository {

    private val apiService = ApiService.create()

    // Customer registration
    suspend fun registerCustomer(user: User): Response<RegisterResponse> {
        return apiService.registerCustomer(user)
    }

    // Seller registration
    suspend fun registerSeller(user: Seller): Response<RegisterResponse> {
        return apiService.registerSeller(user)
    }

    // Login a user
    suspend fun loginUser(user: UserLogin): Response<LoginResponse> {
        return apiService.loginUser(user)  // Sending user object directly
    }

}
