package com.project.pawsitivevibes.model

data class Seller(
    val seller_name: String,
    val seller_email: String,
    val seller_password: String,
    val seller_phone: String,
    val seller_address: String? = null,
    val user_role: String = "Seller"  // Default role is "Seller"
)
