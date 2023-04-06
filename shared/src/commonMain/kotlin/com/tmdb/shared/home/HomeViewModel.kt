package com.tmdb.shared.home

import com.tmdb.shared.core.data.UiState
import com.tmdb.shared.core.viewModel.BaseViewModel
import com.tmdb.shared.home.data.HomeMovieSection
import com.tmdb.shared.home.data.HomeUiData
import com.tmdb.shared.home.data.ios.HomeUiDataIos
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach


class HomeViewModel(
    private val sharedHomeViewModel: SharedHomeViewModel,
) : BaseViewModel() {
    init {
        sharedHomeViewModel.init(coroutineScope)
    }

    val uiState: HomeUiData = sharedHomeViewModel.uiState()

    val uiStateFlow: StateFlow<HomeUiData> = sharedHomeViewModel.uiStateFlow()

    fun observeUiStateIos(onChange: (List<HomeUiDataIos>) -> Unit) {
        uiStateFlow
            .map { data ->
                data.movieSections.map { section ->
                    when (val sectionState = section.value) {
                        is UiState.Error -> HomeUiDataIos.error(section.key)
                        is UiState.NetworkError -> HomeUiDataIos.networkError(section.key)
                        is UiState.Loading -> HomeUiDataIos.loading(section.key)
                        is UiState.Success -> HomeUiDataIos.success(section.key, sectionState.data)
                    }
                }
            }.onEach {
                onChange(it)
            }.launchIn(coroutineScope)
    }

    val onReloadMovieSection: (HomeMovieSection) -> Unit = sharedHomeViewModel.onReloadMovieSection

    override fun onCleared() {
        super.onCleared()
        sharedHomeViewModel.onClear()
    }
}
