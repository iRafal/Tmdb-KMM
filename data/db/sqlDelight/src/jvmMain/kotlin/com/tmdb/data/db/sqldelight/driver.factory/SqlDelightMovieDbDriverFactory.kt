package com.tmdb.data.db.sqldelight.driver.factory

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.tmdb.data.db.sqldelight.MovieDb

actual class SqlDelightMovieDbDriverFactory {
    actual fun createDriver(): SqlDriver {
        return JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY).also { driver ->
            MovieDb.Schema.create(driver)
        }
    }
}