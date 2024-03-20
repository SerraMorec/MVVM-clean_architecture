package com.example.data.api

import com.example.data.models.ProductDTO
import com.example.domain.models.DetailProductDAO
import com.example.domain.models.ProductsDAO

internal fun ProductDTO.toProductDAO(): ProductsDAO {
    return ProductsDAO(
        id = this.id,
        title = this.title,
        description = this.description,
        thumbnailUrl = this.thumbnailUrl
    )
}

internal fun ProductDTO.toDetailProductDAO(): DetailProductDAO {
    return DetailProductDAO(
        id = this.id,
        title = this.title,
        description = this.description,
        price = this.price,
        discount = this.discount,
        rating = this.rating,
        stock = this.stock,
        brand = this.brand,
        category = this.category,
        thumbnailUrl = this.thumbnailUrl,
        images = this.images,
    )
}