package com.project.pawsitivevibes.model

import java.io.Serializable

data class Item(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val quantity: Int,
    val imageUrl: String // If your API returns URLs for product images
)
