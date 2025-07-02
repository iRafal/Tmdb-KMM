package com.tmdb.store.env.di

import com.tmdb.data.source.local.impl.di.localDataSourceModule
import com.tmdb.store.env.contract.AppEnv
import com.tmdb.store.env.impl.createAppDbEnvImpl
import org.koin.dsl.module


fun appDbModule() = module {
    includes(localDataSourceModule())
    single<AppEnv.Database> { createAppDbEnvImpl(get()) }
}
