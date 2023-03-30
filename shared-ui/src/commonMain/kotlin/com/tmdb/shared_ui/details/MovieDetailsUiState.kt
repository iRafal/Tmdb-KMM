package com.tmdb.shared_ui.details

import com.tmdb.shared.details.data.MovieDetailsUiData

sealed interface MovieDetailsUiState {
    object Idle : MovieDetailsUiState
    object Loading : MovieDetailsUiState
    data class Error(val cause: Throwable? = null) : MovieDetailsUiState
    data class NetworkError(val cause: Throwable? = null) : MovieDetailsUiState
    data class Success(val data: MovieDetailsUiData) : MovieDetailsUiState
}