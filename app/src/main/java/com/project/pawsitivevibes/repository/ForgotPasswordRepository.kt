package com.project.pawsitivevibes.repository

import com.project.pawsitivevibes.model.ForgotPasswordRequest
import com.project.pawsitivevibes.model.VerifyEmailRequest
import com.project.pawsitivevibes.network.ApiService
import retrofit2.Response

class ForgotPasswordRepository(private val apiService: ApiService) {

    suspend fun verifyEmail(email: String): Response<ForgotPasswordResponse> {
        val request = VerifyEmailRequest(cust_email = email)
        return apiService.verifyEmail(request)
    }

    suspend fun resetPassword(request: ForgotPasswordRequest): Response<ForgotPasswordResponse> {
        return apiService.resetPassword(request)
    }
}

