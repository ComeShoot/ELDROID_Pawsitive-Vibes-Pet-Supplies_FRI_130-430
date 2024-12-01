package com.project.pawsitivevibes.model

data class ForgotPasswordRequest(
    val cust_email: String,
    val new_password: String,
    val new_password_confirmation: String
)
