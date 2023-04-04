package com.tmdb.shared_ui.core.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.cancel

actual abstract class BaseViewModel : ViewModel() {
    actual val coroutineScope = viewModelScope

    actual fun dispose() {
        coroutineScope.cancel()
        onCleared()
    }

    actual override fun onCleared() {
        super.onCleared()
    }
}