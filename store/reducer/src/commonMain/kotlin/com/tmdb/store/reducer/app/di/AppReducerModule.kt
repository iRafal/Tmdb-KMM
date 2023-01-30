package com.tmdb.store.reducer.app.di

import com.tmdb.store.reducer.app.AppReducer
import com.tmdb.store.reducer.app.createAppReducer
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun appReducerModule() = module {
    single<AppReducer>(named("AppReducer")) { createAppReducer(homeFeatureSlice = get()) }
}