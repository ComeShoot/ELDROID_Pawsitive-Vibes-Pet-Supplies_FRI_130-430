package com.project.pawsitivevibes.model


data class CartItem(
    val cartItemId: Int,
    val storeName: String,
    val productName: String,
    val productDescription: String,
    val productPrice: Double,
    val quantity: Int,
    val productImage: String,
    var isSelected: Boolean = false // To track checkbox state
)

