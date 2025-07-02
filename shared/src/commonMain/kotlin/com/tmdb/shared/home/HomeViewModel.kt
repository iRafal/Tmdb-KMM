package com.tmdb.shared.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tmdb.shared.home.data.HomeMovieSection
import com.tmdb.shared.home.data.HomeUiData
import com.tmdb.shared.home.data.mapping.HomeFeatureToUiStateMapper
import com.tmdb.shared.home.data.mapping.HomeMovieSectionToActionMapper
import com.tmdb.store.action.home.HomeAction
import com.tmdb.store.app.AppStore
import com.tmdb.store.feature.home.HomeFeature
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class HomeViewModel(
    private val store: AppStore,
    private val homeFeatureToUiStateMapper: HomeFeatureToUiStateMapper,
    private val homeMovieSectionToActionMapper: HomeMovieSectionToActionMapper,
    dispatcherIo: CoroutineDispatcher,
) : ViewModel() {
    init {
        with(store) {
            setFeatureScope(HomeFeature, viewModelScope)
            dispatch(HomeAction.LoadMovieSections)
        }
    }

    val uiStateFlow: StateFlow<HomeUiData> = store.stateFlow
        .map { appState -> homeFeatureToUiStateMapper(appState.homeState) }
        .flowOn(dispatcherIo)
        .stateIn(viewModelScope, SharingStarted.Eagerly, HomeUiData.INITIAL)

    val uiState: HomeUiData = uiStateFlow.value

    val onReloadMovieSection: (HomeMovieSection) -> Unit = { movieSection ->
        store.dispatch(homeMovieSectionToActionMapper(movieSection))
    }
}
