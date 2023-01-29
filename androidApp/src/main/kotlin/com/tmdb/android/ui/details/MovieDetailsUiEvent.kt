package com.tmdb.android.ui.details

sealed interface MovieDetailsUiEvent {
    object NavigateBack : MovieDetailsUiEvent
}