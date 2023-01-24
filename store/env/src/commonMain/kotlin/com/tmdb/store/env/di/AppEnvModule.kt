package com.tmdb.store.env.di

import com.tmdb.store.env.contract.AppEnv
import com.tmdb.store.env.impl.createAppEnvImpl
import org.koin.dsl.module

val appEnvModule = module {
    includes(appDbModule, appNetworkModule)
    single<AppEnv> {
        createAppEnvImpl(appNetwork = get(), appDatabase = get())
    }
}