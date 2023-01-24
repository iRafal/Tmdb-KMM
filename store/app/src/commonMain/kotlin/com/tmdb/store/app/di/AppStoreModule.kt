package com.tmdb.store.app.di

import com.tmdb.store.app.AppStore
import com.tmdb.store.app.AppStoreImpl
import com.tmdb.store.env.di.appEnvModule
import com.tmdb.store.reducer.di.appReducerModule
import com.tmdb.store.reducer.di.homeFeatureModule
import com.tmdb.store.state.di.APP_STATE_INITIAL
import com.tmdb.store.state.di.storeStateModule
import com.tmdb.util.di.DISPATCHER_IO
import com.tmdb.util.di.dispatchersModule
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appStoreModule = module {
    includes(homeFeatureModule, appEnvModule, dispatchersModule, storeStateModule, appReducerModule)
    single<AppStore> {
        AppStoreImpl(
            initialState = get(named(APP_STATE_INITIAL)),
            env = get(),
            reducer = get(named("AppReducer")),
            effectContext = get(named(DISPATCHER_IO))
        )
    }
}