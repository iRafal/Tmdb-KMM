package com.tmdb.data.db.sqldelight.di.module

import com.tmdb.data.db.sqldelight.adapters.LocalDateColumnAdapter
import com.tmdb.data.db.sqldelight.adapters.LocalDateTimeColumnAdapter
import com.tmdb.data.db.sqldelight.di.MovieDbProvider
import org.koin.dsl.module

fun sqlDelightModule() = module {
    single { MovieDbProvider.createDriver(get()) }
    single { MovieDbProvider.createDatabase(get(), get()) }
    single { LocalDateColumnAdapter() }
    single { LocalDateTimeColumnAdapter() }
}