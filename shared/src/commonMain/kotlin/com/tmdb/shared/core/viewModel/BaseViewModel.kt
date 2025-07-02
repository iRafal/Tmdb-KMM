package com.tmdb.shared.core.viewModel

import kotlinx.coroutines.CoroutineScope

expect abstract class BaseViewModel() {
    val coroutineScope: CoroutineScope

    fun dispose()

    protected open fun onCleared()
}
