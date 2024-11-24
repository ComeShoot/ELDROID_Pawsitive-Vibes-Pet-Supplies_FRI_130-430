package com.project.pawsitivevibes.repository

import com.project.pawsitivevibes.model.User
import com.project.pawsitivevibes.model.UserLogin
import com.project.pawsitivevibes.network.ApiService
import retrofit2.Response

class UserRepository {

    private val apiService = ApiService.create()

    // Register a user
    suspend fun registerUser(user: User): Response<RegisterResponse> {
        return apiService.registerUser(user)
    }

    // Login a user
    suspend fun loginUser(user: UserLogin): Response<LoginResponse> {
        return apiService.loginUser(user)  // Sending user object directly
    }

}
