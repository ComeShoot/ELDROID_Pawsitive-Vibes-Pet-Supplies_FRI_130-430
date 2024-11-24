package com.project.pawsitivevibes.model

data class TransactionItem(
    val imageResId: Int, // Drawable resource ID for product image
    val merchantName: String,
    val productName: String,
    val productQuantity: String,
    val productDescription: String,
    val totalPrice: String,
    val orderStatus: String // For the button text
)