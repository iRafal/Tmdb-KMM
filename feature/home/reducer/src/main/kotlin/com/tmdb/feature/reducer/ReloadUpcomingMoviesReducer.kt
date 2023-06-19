package com.tmdb.feature.reducer

import com.tmdb.feature.home.action.HomeAction
import com.tmdb.store.base.Effect
import com.tmdb.store.base.Effects
import com.tmdb.store.env.contract.AppEnv
import com.tmdb.store.state.FeatureState
import com.tmdb.store.state.home.HomeFeatureState

fun HomeFeatureState.reduceReloadUpcomingMovies(
    action: HomeAction.ReloadUpcomingMovies
): Pair<HomeFeatureState, Effect<AppEnv>?> {
    return this.copy(upcomingMoviesState = FeatureState.Loading()) to Effects.empty() // TODO effect
}
