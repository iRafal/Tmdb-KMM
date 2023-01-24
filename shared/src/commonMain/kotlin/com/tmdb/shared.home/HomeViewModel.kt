package com.tmdb.shared.home

import com.tmdb.store.action.home.HomeAction
import com.tmdb.store.app.AppStore
import com.tmdb.store.feature.home.HomeFeature
import com.tmdb.store.state.app.AppState
import com.tmdb.store.state.home.HomeFeatureState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map


class HomeViewModel(
    private val store: AppStore,
) {
    private val state: StateFlow<AppState> = store.stateFlow

    val homeFeatureStateFlow: Flow<HomeFeatureState> = state
        .map { appState -> appState.homeState }

    fun init(scope: CoroutineScope) {
        store.setFeatureScope(HomeFeature, scope)
        store.dispatch(HomeAction.LoadMovieSections)
    }
}
