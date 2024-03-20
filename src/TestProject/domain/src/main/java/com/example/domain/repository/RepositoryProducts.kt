package com.example.domain.repository

import androidx.paging.PagingData
import com.example.domain.models.DetailProductDAO
import com.example.domain.models.ProductsDAO
import kotlinx.coroutines.flow.Flow

interface RepositoryProducts {
    fun getPageProducts(): Flow<PagingData<ProductsDAO>>
    fun getDetailProduct(productID: Int): Flow<ApiResult<DetailProductDAO>>
}