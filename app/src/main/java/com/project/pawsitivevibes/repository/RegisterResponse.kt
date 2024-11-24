package com.project.pawsitivevibes.repository

data class RegisterResponse(
    val message: String,
    val customer: Customer? // Define a 'Customer' data class based on the response structure from your API
)

data class Customer(
    val cust_name: String,
    val cust_email: String,
    val cust_password: String,
    val cust_phone: String,
    val cust_address: String
)
