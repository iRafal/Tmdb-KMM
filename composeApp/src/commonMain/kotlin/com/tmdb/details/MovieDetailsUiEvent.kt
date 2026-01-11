package com.tmdb.details

sealed interface MovieDetailsUiEvent {
    data object NavigateBack : MovieDetailsUiEvent
}
