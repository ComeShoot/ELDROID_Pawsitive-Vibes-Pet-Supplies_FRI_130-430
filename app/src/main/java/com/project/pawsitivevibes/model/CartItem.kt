package com.project.pawsitivevibes.model

import java.io.Serializable

data class CartItem(
    val storeName: String,
    val productTitle: String,
    val productDescription: String,
    val productPrice: String,
    val productImage: Int,  // Use a drawable resource id for the image
    val quantity: Int
) : Serializable
