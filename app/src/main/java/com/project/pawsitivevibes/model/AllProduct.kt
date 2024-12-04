package com.project.pawsitivevibes.model

import com.google.gson.annotations.SerializedName

data class AllProduct(
    @SerializedName("prod_id") val prodId: Int,
    @SerializedName("seller_id") val sellerId: Int,
    @SerializedName("prod_name") val prodName: String,
    @SerializedName("prod_description") val prodDescription: String,
    @SerializedName("prod_price") val prodPrice: Double,
    @SerializedName("prod_quantity") val prodQuantity: Int,
    @SerializedName("prod_image") val prodImage: String
)
