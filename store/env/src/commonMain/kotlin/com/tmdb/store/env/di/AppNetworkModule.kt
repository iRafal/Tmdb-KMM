package com.tmdb.store.env.di

import com.tmdb.data.source.remote.impl.di.module.remoteDataSourceModule
import com.tmdb.store.env.contract.AppEnv.Network
import com.tmdb.store.env.impl.createAppNetworkEnvImpl
import org.koin.dsl.module

fun appNetworkModule() = module {
    includes(remoteDataSourceModule())
    single<Network> {
        createAppNetworkEnvImpl(
            discoverSource = get(),
            genreSource = get(),
            movieSource = get(),
            personSource = get(),
        )
    }
}
