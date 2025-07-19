package com.tmdb.data.db.sqldelight.di.module

import com.tmdb.data.db.sqldelight.MovieDb
import com.tmdb.data.db.sqldelight.adapters.LocalDateColumnAdapter
import com.tmdb.data.db.sqldelight.adapters.LocalDateTimeColumnAdapter
import com.tmdb.data.db.sqldelight.di.MovieDbProvider
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val MOVIES_DB_NAME = "movies_db_name"

fun sqlDelightModule() = module {
    includes(sqlDelightDriverFactoryModule())
    single { MovieDbProvider.createDatabase(get(), get()) }
    single { LocalDateColumnAdapter() }
    single { LocalDateTimeColumnAdapter() }
    single { MovieDb.Schema }
    single(named(MOVIES_DB_NAME)) { MovieDbProvider.DB_NAME }
}
