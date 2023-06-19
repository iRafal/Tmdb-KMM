package com.tmdb.feature.reducer

import com.tmdb.feature.home.action.HomeAction
import com.tmdb.store.base.Effect
import com.tmdb.store.base.Effects
import com.tmdb.store.env.contract.AppEnv
import com.tmdb.store.state.FeatureState
import com.tmdb.store.state.home.HomeFeatureState

fun HomeFeatureState.reduceReloadNowPlayingMovies(
    action: HomeAction.ReloadNowPlayingMovies
): Pair<HomeFeatureState, Effect<AppEnv>?> {
    return this.copy(nowPopularMoviesState = FeatureState.Loading()) to Effects.empty() // TODO effect
}
