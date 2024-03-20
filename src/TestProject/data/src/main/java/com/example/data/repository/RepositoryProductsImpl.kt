package com.example.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSourceFactory
import com.example.data.api.PAGES_COUNT
import com.example.data.api.ProductApi
import com.example.data.api.toDetailProductDAO
import com.example.data.api.toProductDAO
import com.example.data.paging.ProductsPageSource
import com.example.domain.models.DetailProductDAO
import com.example.domain.models.ProductsDAO
import com.example.domain.repository.ApiResult
import com.example.domain.repository.RepositoryProducts
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map


class RepositoryProductsImpl @Inject constructor(private val product: ProductApi) :
    RepositoryProducts {
    override fun getPageProducts(): Flow<PagingData<ProductsDAO>> {
        return Pager(
            PagingConfig(pageSize = PAGES_COUNT),
            pagingSourceFactory = { ProductsPageSource(product) }
        ).flow
    }

    override fun getDetailProduct(productID: Int): Flow<ApiResult<DetailProductDAO>> {

        val result = flow {
            emit(ApiResult.Loading())
            val response = product.getDetailProduct(productID)
            emit(
                if (response.isSuccess) {
                    response.getOrThrow()
                        .toDetailProductDAO()
                        .let { ApiResult.Success(it) }
                } else {
                    ApiResult.Error(response.exceptionOrNull())
                }
            )
        }
        return result
    }
}

