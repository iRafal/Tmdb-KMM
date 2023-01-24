package com.tmdb.store.env.di

import com.tmdb.data.source.remote.impl.di.module.dataSourceModule
import com.tmdb.store.env.AppEnv.Network
import com.tmdb.store.env.createAppNetworkEnvImpl
import org.koin.dsl.module

val appNetworkModule = module {
    includes(dataSourceModule)
    single<Network> {
        createAppNetworkEnvImpl(
            discoverSource = get(),
            genreSource = get(),
            movieSource = get(),
            personSource = get(),
        )
    }
}