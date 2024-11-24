package com.project.pawsitivevibes.model

data class TransactionReceive(
    val profileImageRes: Int,
    val merchantStoreName: String,
    val productTitle: String,
    val productDescription: String,
    val productPrice: String,
    val itemQuantity: String,
    val totalPrice: String,
    val deliveryDestination: String
)