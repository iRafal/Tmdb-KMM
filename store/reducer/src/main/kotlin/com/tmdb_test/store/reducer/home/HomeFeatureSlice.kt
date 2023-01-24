package com.tmdb_test.store.reducer.home

import com.tmdb_test.data.model.mapping.movie.MoviesApiToDataStateMapper
import com.tmdb_test.store.action.home.HomeAction
import com.tmdb_test.store.action.home.HomeAction.LoadMovieSections
import com.tmdb_test.store.action.home.HomeAction.MovieSectionsLoaded
import com.tmdb_test.store.action.home.HomeAction.ReloadNowPlayingMovies
import com.tmdb_test.store.action.home.HomeAction.ReloadNowPopularMovies
import com.tmdb_test.store.action.home.HomeAction.ReloadTopRatedMovies
import com.tmdb_test.store.action.home.HomeAction.ReloadUpcomingMovies
import com.tmdb_test.store.base.Action
import com.tmdb_test.store.base.Effect
import com.tmdb_test.store.base.Effects
import com.tmdb_test.store.base.feature.FeatureReducer
import com.tmdb_test.store.base.feature.FeatureSlice
import com.tmdb_test.store.env.contract.AppEnv
import com.tmdb_test.store.state.app.AppState
import com.tmdb_test.store.state.home.HomeFeatureState
import com.tmdb_test.store.state.home.MoviesDataToFeatureStateMapper

interface HomeFeatureSlice : FeatureSlice<AppState, AppEnv, HomeFeatureState>

class HomeFeatureSliceImpl(
    moviesApiToDataStateMapper: MoviesApiToDataStateMapper,
    moviesDataToFeatureStateMapper: MoviesDataToFeatureStateMapper,
    homeFeatureEffects: HomeFeatureEffects
) : HomeFeatureSlice {

    override val reducer: FeatureReducer<AppState, AppEnv, HomeFeatureState> =
        { globalState: AppState,
          action: Action ->
            when (action) {
                is HomeAction -> {
                    globalState.reduce(
                        action,
                        moviesApiToDataStateMapper,
                        moviesDataToFeatureStateMapper,
                        homeFeatureEffects
                    )
                }
                else -> globalState.homeState to Effects.empty()
            }
        }
}

private fun AppState.reduce(
    action: HomeAction,
    moviesApiToDataStateMapper: MoviesApiToDataStateMapper,
    moviesDataToFeatureStateMapper: MoviesDataToFeatureStateMapper,
    homeFeatureEffects: HomeFeatureEffects
): Pair<HomeFeatureState, Effect<AppEnv>?> {
    return when (action) {
        is ReloadNowPlayingMovies -> this.homeState.reduceReloadNowPlayingMovies(action)
        is ReloadNowPopularMovies -> this.homeState.reduceReloadNowPopularMovies(action)
        is ReloadTopRatedMovies -> this.homeState.reduceReloadTopRatedMovies(action)
        is ReloadUpcomingMovies -> this.homeState.reduceReloadUpcomingMovies(action)
        is LoadMovieSections -> {
            this.homeState.reduceLoadMovieSections(
                action,
                moviesApiToDataStateMapper,
                homeFeatureEffects
            )
        }
        is MovieSectionsLoaded -> {
            this.homeState.reduceMovieSectionsLoaded(action, moviesDataToFeatureStateMapper)
        }
    }
}
