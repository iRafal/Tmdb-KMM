package com.tmdb.data.db.sqldelight.di.module

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.tmdb.data.db.sqldelight.MovieDb
import com.tmdb.data.db.sqldelight.di.MovieDbProvider
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun sqlDelightAndroidDbDriverModule() = module {
    factory<SqlDriver> { AndroidSqliteDriver(schema = get(), context = get(), get(named(MOVIES_DB_NAME))) }
}