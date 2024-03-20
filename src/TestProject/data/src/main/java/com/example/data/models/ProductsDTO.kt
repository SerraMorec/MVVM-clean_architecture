package com.example.data.models

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
class Product(
    val products: List<ProductDTO>,
    val total: Int,
    val skip: Int,
    val limit: Int
)

@Serializable
class ProductDTO(
    val id: Int,
    val title: String,
    val description: String,
    val price: Int,
    @SerializedName("discountPercentage") val discount: Float,
    val rating: Float,
    val stock: Int,
    val brand: String,
    val category: String,
    @SerializedName("thumbnail") val thumbnailUrl: String,
    val images: List<String?>
)