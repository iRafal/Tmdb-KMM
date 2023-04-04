package com.tmdb.data.db.sqldelight.di.module

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

actual fun sqlDelightDriverFactoryModule(): Module = module {
    factory<SqlDriver> {
        AndroidSqliteDriver(
            schema = get(),
            context = get(),
            get(named(MOVIES_DB_NAME))
        )
    }
}