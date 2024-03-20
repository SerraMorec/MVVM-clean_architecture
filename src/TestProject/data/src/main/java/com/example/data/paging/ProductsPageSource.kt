package com.example.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.data.api.PAGES_COUNT
import com.example.data.api.ProductApi
import com.example.data.api.toProductDAO
import com.example.domain.models.ProductsDAO
import jakarta.inject.Inject

class ProductsPageSource @Inject constructor(private val product: ProductApi) :
    PagingSource<Int, ProductsDAO>() {
    override fun getRefreshKey(state: PagingState<Int, ProductsDAO>): Int? {
        val position = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(position) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ProductsDAO> {

        val page: Int = params.key ?: 0
        val pageSize: Int = params.loadSize.coerceAtMost(PAGES_COUNT)
        val result = product.getPageProducts(skip = page * pageSize, limit = pageSize)
        if (result.isSuccess) {
            val data = checkNotNull(result.getOrNull()).products.map { it.toProductDAO() }
            val next = if (data.size < pageSize) null else page + 1
            val prev = if (page == 0) null else page - 1
            return LoadResult.Page(data, prev, next)
        } else {
            return LoadResult.Error(result.exceptionOrNull()!!)
        }
    }


}