package com.tmdb.store.app

import com.tmdb.store.base.Effect.Executor
import com.tmdb.store.base.Effect.Executor.Scope
import com.tmdb.store.base.Store
import com.tmdb.store.env.AppEnv
import com.tmdb.store.state.AppState

interface AppStore : Store<AppState, AppEnv>, Executor<AppEnv>, Scope<AppEnv>