package com.tmdb.store.env.di

import com.tmdb.store.env.contract.AppEnv
import com.tmdb.store.env.impl.createAppDbEnvImpl
import org.koin.dsl.module


val appDbModule = module {
    single<AppEnv.Database> { createAppDbEnvImpl() }
}