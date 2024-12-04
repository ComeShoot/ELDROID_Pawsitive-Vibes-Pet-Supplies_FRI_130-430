package com.project.pawsitivevibes.model

data class Product(
    val seller_id: Int,
    val prod_id: Int,
    val prod_name: String,
    val prod_description: String,
    val prod_price: Double,
    val prod_quantity: Int,
    val prod_image: String // URL of the image
)
