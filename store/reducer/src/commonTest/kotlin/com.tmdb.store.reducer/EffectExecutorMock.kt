package com.tmdb.store.reducer

import com.tmdb.data.source.local.contract.MovieLocalDataSource
import com.tmdb.data.source.remote.contract.discover.DiscoverRemoteDataSource
import com.tmdb.data.source.remote.contract.genre.GenreRemoteDataSource
import com.tmdb.data.source.remote.contract.movie.MovieRemoteDataSource
import com.tmdb.data.source.remote.contract.person.PersonRemoteDataSource
import com.tmdb.store.base.Action
import com.tmdb.store.base.Effect
import com.tmdb.store.base.feature.Feature
import com.tmdb.store.env.contract.AppEnv
import kotlinx.coroutines.runBlocking

interface EffectExecutorMock : Effect.Executor<AppEnv> {
    val effectExecutorScope: Effect.Executor.Scope<AppEnv>
}

fun createMockEffectExecutor(
    discoverSource: DiscoverRemoteDataSource,
    genreSource: GenreRemoteDataSource,
    movieSource: MovieRemoteDataSource,
    personSource: PersonRemoteDataSource,
    movieLocalDataSource: MovieLocalDataSource,
    dispatchMethodCallCountCallback: (Int) -> Unit
): EffectExecutorMock {

    val database = object : AppEnv.Database {
        override val movieSource: MovieLocalDataSource = movieLocalDataSource
    }

    val network = object: AppEnv.Network {
        override val discoverSource: DiscoverRemoteDataSource = discoverSource
        override val genreSource: GenreRemoteDataSource = genreSource
        override val movieSource: MovieRemoteDataSource = movieSource
        override val personSource: PersonRemoteDataSource = personSource
    }

    val appEnv = object: AppEnv {
        override val network: AppEnv.Network = network
        override val database: AppEnv.Database = database
    }

    val effectExecutorScope = object: Effect.Executor.Scope<AppEnv> {
        override val env: AppEnv = appEnv

        private var dispatchMethodCallCount: Int = 0

        override fun dispatch(action: Action) {
            dispatchMethodCallCountCallback(++dispatchMethodCallCount)
        }
    }

    return object : EffectExecutorMock {
        override val effectExecutorScope: Effect.Executor.Scope<AppEnv> = effectExecutorScope

        override fun execute(
            feature: Feature,
            effectBlock: suspend Effect.Executor.Scope<AppEnv>.() -> Unit
        ) {
            runBlocking {
                effectBlock(effectExecutorScope)
            }
        }
    }
}
