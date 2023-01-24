package com.tmdb.store.app

import com.tmdb.store.base.Effect.Executor
import com.tmdb.store.base.Effect.Executor.Scope
import com.tmdb.store.base.Store
import com.tmdb.store.env.contract.AppEnv
import com.tmdb.store.state.app.AppState

interface AppStore : Store<AppState, AppEnv>, Executor<AppEnv>, Scope<AppEnv>