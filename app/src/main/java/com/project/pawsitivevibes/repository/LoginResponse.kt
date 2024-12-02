package com.project.pawsitivevibes.repository

data class LoginResponse(
    val token: String?,
    val role: String,  // Add this field for role
    val message: String? = null,
    val seller_id: String? = null
)
