package com.tmdb.data.db.sqldelight.di

import com.tmdb.data.db.sqldelight.di.module.sqlDelightIosFactoryModule
import org.koin.core.module.Module

actual fun driverFactoryModuleProvider(): Module = sqlDelightIosFactoryModule()