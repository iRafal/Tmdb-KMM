package com.tmdb.data.db.sqldelight.di.module

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.db.SqlSchema
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import org.koin.dsl.module

fun sqlDelightJvmDbDriverModule() = module {
    factory< SqlDriver> {
        JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY).also { driver ->
            get<SqlSchema>().create(driver)
        }
    }
}