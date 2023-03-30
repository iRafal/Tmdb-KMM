package com.tmdb.shared.core.data


sealed interface UiState<T> {
    class Loading<T> : UiState<T> {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is Loading<*>) return false
            return true
        }

        override fun hashCode(): Int = this::class.hashCode()
    }
    data class Error<T>(val cause: Throwable? = null) : UiState<T>
    data class NetworkError<T>(val cause: Throwable? = null) : UiState<T>
    data class Success<T>(val data: T) : UiState<T>
}