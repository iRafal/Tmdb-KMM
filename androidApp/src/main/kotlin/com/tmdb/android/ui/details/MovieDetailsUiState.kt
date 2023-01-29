package com.tmdb.android.ui.details

import com.tmdb.ui.shared.details.data.MovieDetailsUiData

sealed interface MovieDetailsUiState {
    object Idle : MovieDetailsUiState
    object Loading : MovieDetailsUiState
    data class Error(val cause: Throwable? = null) : MovieDetailsUiState
    data class NetworkError(val cause: Throwable? = null) : MovieDetailsUiState
    data class Success(val data: MovieDetailsUiData) : MovieDetailsUiState
}