package com.sugarspoon.bottom.sheet.sample

data class MainActivityState(
    val isExpanded: Boolean = false,
) {
    fun expand() = copy(isExpanded = true)

    fun collapse() = copy(isExpanded = false)
}