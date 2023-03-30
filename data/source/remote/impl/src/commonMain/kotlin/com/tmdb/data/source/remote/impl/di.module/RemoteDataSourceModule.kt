package com.tmdb.data.source.remote.impl.di.module

import com.tmdb.data.api.impl.di.module.ktorApiModule
import com.tmdb.data.source.remote.contract.discover.DiscoverRemoteDataSource
import com.tmdb.data.source.remote.contract.genre.GenreRemoteDataSource
import com.tmdb.data.source.remote.contract.movie.MovieRemoteDataSource
import com.tmdb.data.source.remote.contract.person.PersonRemoteDataSource
import com.tmdb.data.source.remote.impl.discover.DiscoverRemoteDataSourceImpl
import com.tmdb.data.source.remote.impl.genre.GenreRemoteDataSourceImpl
import com.tmdb.data.source.remote.impl.movie.MovieRemoteDataSourceImpl
import com.tmdb.data.source.remote.impl.person.PersonRemoteDataSourceImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

fun remoteDataSourceModule() = module {
    includes(ktorApiModule())
    singleOf(::DiscoverRemoteDataSourceImpl) { bind<DiscoverRemoteDataSource>() }
    singleOf(::GenreRemoteDataSourceImpl) { bind<GenreRemoteDataSource>() }
    singleOf(::MovieRemoteDataSourceImpl) { bind<MovieRemoteDataSource>() }
    singleOf(::PersonRemoteDataSourceImpl) { bind<PersonRemoteDataSource>() }
}