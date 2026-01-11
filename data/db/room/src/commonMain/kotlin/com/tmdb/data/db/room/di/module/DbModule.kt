package com.tmdb.data.db.room.di.module

import com.tmdb.data.db.room.MovieDb
import com.tmdb.data.db.room.movie.MovieDao
import com.tmdb.util.dispatcher.di.DISPATCHER_IO
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun dbModule() = module {
    includes(roomBuilderModule())
    factory<MovieDb> { MovieDb.getRoomDatabase(get(), get(named(DISPATCHER_IO))) }
    factory<MovieDao> { get<MovieDb>().movieDao() }
}
