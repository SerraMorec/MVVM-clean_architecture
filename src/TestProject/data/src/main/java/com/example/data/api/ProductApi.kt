package com.example.data.api

import com.example.data.models.Product
import com.example.data.models.ProductDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val PAGES_COUNT = 20

interface ProductApi {
    @GET("products")
    suspend fun getPageProducts(
        @Query("skip") skip: Int = 0,
        @Query("limit") limit: Int = PAGES_COUNT,
    ): Result<Product>

    @GET("products/{id}")
    suspend fun getDetailProduct(
        @Path("id") id: Int
    ): Result<ProductDTO>
}