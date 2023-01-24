package com.tmdb.store.state.di

import com.tmdb.store.state.AppState
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val APP_STATE_INITIAL = "APP_STATE_INITIAL"

val storeStateModule = module {
    single<AppState>(named(APP_STATE_INITIAL)) { AppState.createInitialState() }
}