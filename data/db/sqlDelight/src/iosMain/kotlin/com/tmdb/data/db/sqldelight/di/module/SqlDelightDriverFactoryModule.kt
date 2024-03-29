package com.tmdb.data.db.sqldelight.di.module

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

actual fun sqlDelightDriverFactoryModule(): Module =  module {
    factory< SqlDriver> {
        NativeSqliteDriver(schema = get(), name = get(named(MOVIES_DB_NAME)))
    }
}