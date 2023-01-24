package com.tmdb.store.env

import com.tmdb.data.source.remote.contract.discover.DiscoverRemoteDataSource
import com.tmdb.data.source.remote.contract.genre.GenreRemoteDataSource
import com.tmdb.data.source.remote.contract.movie.MovieRemoteDataSource
import com.tmdb.data.source.remote.contract.person.PersonRemoteDataSource

fun createAppNetworkEnvImpl(
    discoverSource: DiscoverRemoteDataSource,
    genreSource: GenreRemoteDataSource,
    movieSource: MovieRemoteDataSource,
    personSource: PersonRemoteDataSource,
): AppEnv.Network = object : AppEnv.Network {
    override val discoverSource = discoverSource
    override val genreSource = genreSource
    override val movieSource = movieSource
    override val personSource = personSource
}