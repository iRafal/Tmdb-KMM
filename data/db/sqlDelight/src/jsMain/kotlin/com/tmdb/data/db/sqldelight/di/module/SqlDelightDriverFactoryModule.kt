package com.tmdb.data.db.sqldelight.di.module

import app.cash.sqldelight.driver.sqljs.initSqlDriver
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun sqlDelightDriverFactoryModule(): Module = module {
    factory {
        GlobalScope.async {
            initSqlDriver(schema = get())
        }.getCompleted()
    }
}