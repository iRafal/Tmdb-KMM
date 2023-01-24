package com.tmdb.store.reducer.home

import com.tmdb.data.model.mapping.movie.MoviesApiToDataStateMapper
import com.tmdb.store.action.home.HomeAction.MovieSectionsLoaded
import com.tmdb.store.base.Action
import com.tmdb.store.base.Effect
import com.tmdb.store.base.Effects
import com.tmdb.store.env.contract.AppEnv
import com.tmdb.store.feature.home.HomeFeature
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext


class HomeFeatureEffects(private val dispatcher: CoroutineDispatcher) {
    fun loadMovieSections(
        mapper: MoviesApiToDataStateMapper
    ) = mainEffect {
        withContext(dispatcher) {
            val nowPlayingMovies = async { env.network.movieSource.nowPlayingMovies() }.await()
            val nowPopularMovies = async { env.network.movieSource.nowPopularMovies() }.await()
            val topRatedMovies = async { env.network.movieSource.topRatedMovies() }.await()
            val upcomingMovies = async { env.network.movieSource.upcomingMovies() }.await()

            MovieSectionsLoaded(
                nowPlayingMovies = mapper(nowPlayingMovies),
                nowPopularMovies = mapper(nowPopularMovies),
                topRatedMovies = mapper(topRatedMovies),
                upcomingMovies = mapper(upcomingMovies),
            )
        }
    }

    private fun mainEffect(
        effectExecutorScope: suspend Effect.Executor.Scope<AppEnv>.() -> Action
    ): Effect<AppEnv> = Effects.effect(effectExecutorScope, HomeFeature)
}
