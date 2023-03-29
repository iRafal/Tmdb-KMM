package com.tmdb.data.db.sqldelight.driver.factory

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.tmdb.data.db.sqldelight.MovieDb
import com.tmdb.data.db.sqldelight.di.MovieDbProvider

actual class SqlDelightMovieDbDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(MovieDb.Schema, context, MovieDbProvider.DB_NAME)
    }
}