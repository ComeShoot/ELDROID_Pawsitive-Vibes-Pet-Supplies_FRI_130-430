package com.project.pawsitivevibes.model

data class User(
    val cust_name: String,        // Required for registration
    val cust_email: String,       // Required for registration
    val cust_password: String,    // Required for registration
    val cust_phone: String,       // Required for registration
    val cust_address: String? = null,  // Optional, can be null for registration
    val user_role: String
)

