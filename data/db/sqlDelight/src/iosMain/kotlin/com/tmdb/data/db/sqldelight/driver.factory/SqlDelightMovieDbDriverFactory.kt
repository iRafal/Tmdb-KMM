package com.tmdb.data.db.sqldelight.driver.factory

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.tmdb.data.db.sqldelight.MovieDb
import com.tmdb.data.db.sqldelight.di.MovieDbProvider

actual class SqlDelightMovieDbDriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(MovieDb.Schema, MovieDbProvider.DB_NAME)
    }
}