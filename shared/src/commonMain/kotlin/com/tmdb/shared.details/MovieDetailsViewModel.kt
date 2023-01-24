package com.tmdb.shared.details

import com.tmdb.store.app.AppStore
import com.tmdb.store.feature.details.MovieDetailsFeature
import com.tmdb.store.state.app.AppState
import com.tmdb.store.state.details.MovieDetailsFeatureState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map


class MovieDetailsViewModel(private val store: AppStore) {
    private val state: StateFlow<AppState> = store.stateFlow

    val movieDetailsFeatureStateFlow: Flow<MovieDetailsFeatureState> = state
        .map { appState -> appState.movieDetailsState }

    fun init(scope: CoroutineScope) {
        store.setFeatureScope(MovieDetailsFeature, scope)
    }
}