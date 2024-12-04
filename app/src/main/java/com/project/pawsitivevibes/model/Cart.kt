package com.project.pawsitivevibes.model

import com.google.gson.annotations.SerializedName

data class Cart(
    @SerializedName("cart_id") val cartId: Int,
    @SerializedName("cust_id") val custId: Int,
    @SerializedName("cart_created_at") val cartCreatedAt: String,
    @SerializedName("cart_updated_at") val cartUpdatedAt: String,
    @SerializedName("cart_items") val cartItems: List<CartItem> // List of items in the cart
)
