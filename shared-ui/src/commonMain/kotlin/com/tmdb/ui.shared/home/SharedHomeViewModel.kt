package com.tmdb.ui.shared.home

import com.tmdb.store.action.home.HomeAction
import com.tmdb.store.app.AppStore
import com.tmdb.store.feature.home.HomeFeature
import com.tmdb.store.state.app.AppState
import com.tmdb.store.state.home.HomeFeatureState
import com.tmdb.ui.shared.home.data.HomeMovieSection
import com.tmdb.ui.shared.home.data.HomeUiData
import com.tmdb.ui.shared.home.data.mapping.HomeFeatureToUiStateMapper
import com.tmdb.ui.shared.home.data.mapping.HomeMovieSectionToActionMapper
import kotlin.jvm.JvmSuppressWildcards
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn


class SharedHomeViewModel(
    private val store: AppStore,
    private val homeFeatureToUiStateMapper: HomeFeatureToUiStateMapper,
    private val dispatcherIo: CoroutineDispatcher,
    private val homeMovieSectionToActionMapper: HomeMovieSectionToActionMapper,
) {
    private var coroutineScope: CoroutineScope? = null

    val onReloadMovieSection: (HomeMovieSection) -> Unit = { movieSection ->
        passAction(homeMovieSectionToActionMapper(movieSection))
    }

    fun init(scope: CoroutineScope) {
        coroutineScope = scope
        store.setFeatureScope(HomeFeature, scope)
        passAction(HomeAction.LoadMovieSections)
    }

    fun uiState(): HomeUiData = uiStateFlow().value

    fun uiStateFlow(): StateFlow<HomeUiData> = store.stateFlow
        .map { appState -> homeFeatureToUiStateMapper(appState.homeState) }
        .flowOn(dispatcherIo)
        .stateIn(checkNotNull(coroutineScope), SharingStarted.Eagerly, HomeUiData.INITIAL)

    private fun passAction(homeAction: HomeAction) {
        store.dispatch(homeAction)
    }

    fun onClear() {
        coroutineScope = null
    }
}
