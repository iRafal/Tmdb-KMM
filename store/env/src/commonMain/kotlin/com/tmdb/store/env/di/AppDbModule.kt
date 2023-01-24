package com.tmdb.store.env.di

import com.tmdb.store.env.AppEnv
import com.tmdb.store.env.createAppDbEnvImpl
import org.koin.dsl.module


val appDbModule = module {
    single<AppEnv.Database> { createAppDbEnvImpl() }
}