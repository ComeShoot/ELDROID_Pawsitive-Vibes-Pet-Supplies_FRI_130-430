package com.project.pawsitivevibes.model

import java.io.Serializable

data class Item(
    val title: String,
    val description: String,
    val imageResId: Int, // Drawable resource ID for images
    val price: String,
    val quantity: String,
) : Serializable
