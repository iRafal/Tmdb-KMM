package com.tmdb.store.state.app.di

import com.tmdb.store.state.app.AppState
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val APP_STATE_INITIAL = "APP_STATE_INITIAL"

fun storeStateModule() = module {
    single<AppState>(named(APP_STATE_INITIAL)) { AppState.createInitialState() }
}
