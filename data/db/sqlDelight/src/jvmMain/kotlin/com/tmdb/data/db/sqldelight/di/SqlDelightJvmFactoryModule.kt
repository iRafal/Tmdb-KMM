package com.tmdb.data.db.sqldelight.di

import com.tmdb.data.db.sqldelight.driver.factory.SqlDelightMovieDbDriverFactory
import org.koin.dsl.module

fun sqlDelightJvmFactoryModule() = module {
    factory { SqlDelightMovieDbDriverFactory() }
}