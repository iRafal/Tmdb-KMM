package com.tmdb.data.db.sqldelight.di.module

import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.db.SqlSchema
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun sqlDelightDriverFactoryModule(): Module = module {
    factory<SqlDriver> {
        JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY).also { driver ->
            get<SqlSchema<QueryResult.AsyncValue<Unit>>>().create(driver)
        }
    }
}