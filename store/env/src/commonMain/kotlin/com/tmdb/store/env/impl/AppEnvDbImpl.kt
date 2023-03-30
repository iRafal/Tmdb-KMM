package com.tmdb.store.env.impl

import com.tmdb.data.source.local.contract.MovieLocalDataSource
import com.tmdb.store.env.contract.AppEnv.Database

fun createAppDbEnvImpl(
    movieSource: MovieLocalDataSource
): Database = object : Database {
    override val movieSource: MovieLocalDataSource = movieSource
}