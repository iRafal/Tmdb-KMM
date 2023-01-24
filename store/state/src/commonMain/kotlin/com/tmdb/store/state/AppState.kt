package com.tmdb.store.state

import com.tmdb.store.state.details.MovieDetailsFeatureState
import com.tmdb.store.state.home.HomeFeatureState


data class AppState(
    val homeState: HomeFeatureState,
    val movieDetailsState: MovieDetailsFeatureState
) {
    companion object {
        fun createInitialState() = INITIAL

        val INITIAL = AppState(
            homeState = HomeFeatureState.INITIAL,
            movieDetailsState = MovieDetailsFeatureState.INITIAL
        )
    }
}