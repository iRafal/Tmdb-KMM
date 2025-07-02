package com.tmdb.shared.details

sealed interface MovieDetailsUiEvent {
    data object NavigateBack : MovieDetailsUiEvent
}
