package com.example.presentation.detail

import com.example.domain.models.DetailProductDAO

sealed class State {
    class Success(val data: DetailProductDAO) : State()
    class Error(val error: Throwable?) : State()
    class Loading(val data: DetailProductDAO? =null) : State()
    object None : State()
}