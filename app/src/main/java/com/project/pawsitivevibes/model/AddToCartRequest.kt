package com.project.pawsitivevibes.model

data class AddToCartRequest(
    val cust_id: Int,
    val prod_id: Int,
    val cart_item_quantity: Int,
    val cart_item_price: Double
)
