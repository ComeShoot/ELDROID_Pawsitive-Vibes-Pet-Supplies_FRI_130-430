package com.project.pawsitivevibes.model

import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @SerializedName("message") val message: String,
    @SerializedName("cart") val cart: Cart?,
    @SerializedName("cart_item") val cartItem: CartItem?
)
