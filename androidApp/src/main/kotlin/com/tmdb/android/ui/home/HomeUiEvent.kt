package com.tmdb.android.ui.home

import com.tmdb.shared.home.data.HomeMovieSection

sealed interface HomeUiEvent {
    object NavigateBack : HomeUiEvent
    data class ReloadMovieSection(val movieSection: HomeMovieSection): HomeUiEvent
    data class OpenMovie(val id: Int): HomeUiEvent
}