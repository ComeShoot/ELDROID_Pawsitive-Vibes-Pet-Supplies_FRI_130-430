package com.project.pawsitivevibes.model

import com.google.gson.annotations.SerializedName

data class CartItemsResponse(
    @SerializedName("cart_items")
    val cartItems: List<CartItem>
)

