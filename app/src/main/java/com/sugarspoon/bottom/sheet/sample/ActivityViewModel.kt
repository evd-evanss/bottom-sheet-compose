package com.sugarspoon.bottom.sheet.sample

class ActivityViewModel : BaseViewModel<MainActivityState>(MainActivityState()) {

    fun expandBottomSheet() {
        updateUiState { it.expand() }
    }

    fun collapseBottomSheet() {
        updateUiState { it.collapse() }
    }
}