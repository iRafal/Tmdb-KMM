package com.tmdb.shared.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tmdb.store.app.AppStore
import com.tmdb.store.feature.details.MovieDetailsFeature
import com.tmdb.store.state.details.MovieDetailsFeatureState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class MovieDetailsViewModel(
    private val movieId: String,
    private val store: AppStore,
) : ViewModel() {

    init {
        with(store) {
            setFeatureScope(MovieDetailsFeature, viewModelScope)
        }
    }

    val stateFlow: StateFlow<MovieDetailsFeatureState> = store.stateFlow
        .map { it.movieDetailsState }
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            MovieDetailsFeatureState.INITIAL,
        )
}
