package com.tmdb.data.db.sqldelight.di

import app.cash.sqldelight.db.SqlDriver
import com.tmdb.data.db.sqldelight.Movie.Adapter
import com.tmdb.data.db.sqldelight.MovieDb
import com.tmdb.data.db.sqldelight.adapters.LocalDateColumnAdapter

object MovieDbProvider {
    internal const val DB_NAME = "Movie.db"

    fun createDatabase(
        driver: SqlDriver,
        localDateColumnAdapter: LocalDateColumnAdapter
    ): MovieDb = MovieDb(driver, Adapter(localDateColumnAdapter))
}