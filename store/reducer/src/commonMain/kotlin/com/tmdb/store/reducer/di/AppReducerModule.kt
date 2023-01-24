package com.tmdb.store.reducer.di

import com.tmdb.store.reducer.AppReducer
import com.tmdb.store.reducer.createAppReducer
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appReducerModule = module {
    single<AppReducer>(named("AppReducer")) { createAppReducer(homeFeatureSlice = get()) }
}