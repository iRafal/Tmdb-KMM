package com.tmdb.data.db.sqldelight.driver.factory

import app.cash.sqldelight.db.SqlDriver

expect class SqlDelightMovieDbDriverFactory {
    fun createDriver(): SqlDriver
}