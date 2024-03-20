package com.example.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.DetailProductDAO
import com.example.domain.repository.ApiResult
import com.example.domain.usecases.GetDetailProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val useCase: GetDetailProductUseCase
) : ViewModel() {
    private var _details = MutableStateFlow<State>(State.None)
    val details = _details.asStateFlow()

    private var id = -1

    fun SetId(newId: Int) {
        if (newId != id) {
            id = newId
            loadDetails(id)
        }
    }

    private fun ApiResult<DetailProductDAO>.toState(): State {
        return when (this) {
            is ApiResult.Error -> State.Error(this.error)
            is ApiResult.Success<DetailProductDAO> -> State.Success(this.data)
            is ApiResult.Loading -> State.Loading()
        }
    }

    fun loadDetails(productID: Int) {
        viewModelScope.launch {
            useCase
                .execute(productID)
                .collect{ _details.value = it.toState()}
        }
    }

}
