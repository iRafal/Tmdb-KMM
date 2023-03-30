package com.tmdb.shared_ui.details

sealed interface MovieDetailsUiEvent {
    object NavigateBack : MovieDetailsUiEvent
}