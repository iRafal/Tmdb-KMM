package com.tmdb.shared.details

import com.tmdb.store.app.AppStore
import com.tmdb.store.feature.details.MovieDetailsFeature
import com.tmdb.store.state.details.MovieDetailsFeatureState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn


class SharedMovieDetailsViewModel(
    private val store: AppStore,
) {
    private var coroutineScope: CoroutineScope? = null

    fun init(scope: CoroutineScope) {
        coroutineScope = scope
        store.setFeatureScope(MovieDetailsFeature, scope)
    }

    fun state(): StateFlow<MovieDetailsFeatureState> = store.stateFlow
        .map { it.movieDetailsState }
        .stateIn(
            checkNotNull(coroutineScope),
            SharingStarted.Eagerly,
            MovieDetailsFeatureState.INITIAL
        )

    fun onClear() {
        coroutineScope = null
    }
}