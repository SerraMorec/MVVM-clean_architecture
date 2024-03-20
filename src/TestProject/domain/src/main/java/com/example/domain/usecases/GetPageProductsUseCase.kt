package com.example.domain.usecases

import androidx.paging.PagingData
import com.example.domain.models.ProductsDAO
import com.example.domain.repository.RepositoryProducts
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetPageProductsUseCase @Inject constructor(
    private val repository: RepositoryProducts
) {
    fun execute(): Flow<PagingData<ProductsDAO>> {
        return repository.getPageProducts()
    }
}