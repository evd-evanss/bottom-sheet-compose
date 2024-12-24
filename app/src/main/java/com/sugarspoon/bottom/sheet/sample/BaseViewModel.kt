package com.sugarspoon.bottom.sheet.sample

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseViewModel<T : Any>(initialState: T) : ViewModel() {

    private val _uiState = MutableStateFlow(initialState)
    val uiState: StateFlow<T>
        get() = _uiState.asStateFlow()

    protected fun updateUiState(update: (T) -> T) {
        _uiState.value = update(_uiState.value)
    }
}