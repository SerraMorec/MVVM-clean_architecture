package com.example.domain.usecases

import com.example.domain.models.DetailProductDAO
import com.example.domain.repository.ApiResult
import com.example.domain.repository.RepositoryProducts
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetDetailProductUseCase @Inject constructor(
    private val repositoryProducts: RepositoryProducts
) {
    fun execute(productID: Int): Flow<ApiResult<DetailProductDAO>> {
        return repositoryProducts.getDetailProduct(productID)
    }
}