package com.example.domain.repository

sealed class ApiResult<T> {
    class Success<T>(val data: T) : ApiResult<T>()
    class Error<T>(val error: Throwable?) : ApiResult<T>()
    class Loading<T>:ApiResult<T>()
}
