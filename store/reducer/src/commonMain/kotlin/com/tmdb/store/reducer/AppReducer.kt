package com.tmdb.store.reducer

import com.tmdb.store.base.Effects
import com.tmdb.store.base.Reducer
import com.tmdb.store.env.AppEnv
import com.tmdb.store.state.AppState
import com.tmdb.store.reducer.details.MovieDetailsFeatureSlice
import com.tmdb.store.reducer.home.HomeFeatureSlice

typealias AppReducer = Reducer<AppState, AppEnv>

fun createAppReducer(homeFeatureSlice: HomeFeatureSlice): AppReducer {
    return { state, action ->
        val (homeState, homeEffect) = homeFeatureSlice.reducer(state, action)
        val (movieDetailsState, movieDetailsEffect) = MovieDetailsFeatureSlice.reducer(
            state,
            action
        )

        val newGlobalState =
            state.copy(homeState = homeState, movieDetailsState = movieDetailsState)
        newGlobalState to Effects.merge(homeEffect, movieDetailsEffect)
    }
}