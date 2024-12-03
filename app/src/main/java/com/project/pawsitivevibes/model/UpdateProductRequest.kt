package com.project.pawsitivevibes.model

data class UpdateProductRequest(
    val name: String,
    val description: String,
    val price: Double,
    val quantity: Int
)