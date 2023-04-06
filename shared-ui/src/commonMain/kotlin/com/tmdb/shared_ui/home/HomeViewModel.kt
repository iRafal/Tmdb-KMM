package com.tmdb.shared_ui.home

import com.tmdb.shared.home.SharedHomeViewModel
import com.tmdb.shared.home.data.HomeMovieSection
import com.tmdb.shared.home.data.HomeUiData
import com.tmdb.shared.core.viewModel.BaseViewModel
import kotlinx.coroutines.flow.StateFlow


class HomeViewModel(
    private val sharedHomeViewModel: SharedHomeViewModel,
) : BaseViewModel() {
    init {
        sharedHomeViewModel.init(coroutineScope)
    }

    val uiState: HomeUiData = sharedHomeViewModel.uiState()

    val uiStateFlow: StateFlow<HomeUiData> = sharedHomeViewModel.uiStateFlow()

    val onReloadMovieSection: (HomeMovieSection) -> Unit = sharedHomeViewModel.onReloadMovieSection

    override fun onCleared() {
        super.onCleared()
        sharedHomeViewModel.onClear()
    }
}
