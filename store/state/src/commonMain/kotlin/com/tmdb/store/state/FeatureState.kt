package com.tmdb.store.state

sealed interface FeatureState<T> {
    class Loading<T> : FeatureState<T> {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other == null || this::class != other::class) return false
            return true
        }

        override fun hashCode(): Int = this::class.hashCode()
    }

    data class Error<T>(val cause: Throwable? = null) : FeatureState<T>
    data class NetworkError<T>(val cause: Throwable? = null) : FeatureState<T>
    data class Success<T>(val data: T) : FeatureState<T>

    val isLoading
        get() = this is Loading

    val isError
        get() = this is Error

    val isNetworkError
        get() = this is NetworkError

    val isSuccess
        get() = this is Success
}
