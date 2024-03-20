package com.example.domain.models

class DetailProductDAO (
    val id: Int,
    val title: String,
    val description: String,
    val price: Int,
    val discount: Float,
    val rating: Float,
    val stock: Int,
    val brand: String,
    val category: String,
    val thumbnailUrl: String,
    val images: List<String?>
)