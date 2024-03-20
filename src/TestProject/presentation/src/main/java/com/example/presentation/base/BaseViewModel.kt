package com.example.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.domain.models.ProductsDAO
import com.example.domain.usecases.GetPageProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BaseViewModel @Inject constructor(private val useCase: GetPageProductsUseCase) : ViewModel() {
    private var _products = MutableStateFlow<PagingData<ProductsDAO>>(PagingData.empty())
    val products = _products.asStateFlow()

    fun loadProducts() {
        viewModelScope.launch {
            useCase
                .execute()
                .cachedIn(this)
                .collect { _products.value = it }
        }
    }

    init {
        loadProducts()
    }
}
