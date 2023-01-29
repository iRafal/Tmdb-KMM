package com.tmdb.android.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tmdb.ui.shared.home.SharedHomeViewModel
import com.tmdb.ui.shared.home.data.HomeMovieSection
import com.tmdb.ui.shared.home.data.HomeUiData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val sharedHomeViewModel: SharedHomeViewModel,
) : ViewModel() {
    init {
        sharedHomeViewModel.init(viewModelScope)
    }

    val uiState: HomeUiData = sharedHomeViewModel.uiState()

    val uiStateFlow: StateFlow<HomeUiData> = sharedHomeViewModel.uiStateFlow()

    val onReloadMovieSection: (HomeMovieSection) -> Unit = sharedHomeViewModel.onReloadMovieSection

    override fun onCleared() {
        super.onCleared()
        sharedHomeViewModel.onClear()
    }
}
