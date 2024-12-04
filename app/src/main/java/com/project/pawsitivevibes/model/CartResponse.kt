package com.project.pawsitivevibes.model

data class CartResponse(
    val message: String,
    val cartItems: List<CartItem>
)

data class CartItems(
    val cart_id: Int,
    val prod_id: Int,
    val cart_item_quantity: Int,
    val cart_item_price: Double,
    val created_at: String?,
    val updated_at: String?,
    val id: Int
)
