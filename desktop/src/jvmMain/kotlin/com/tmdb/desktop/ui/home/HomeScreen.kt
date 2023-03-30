package com.tmdb.desktop.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.tmdb.shared.home.SharedHomeViewModel
import com.tmdb.shared.home.data.HomeUiData
import com.tmdb.shared_ui.core.theme.Tmdb_TestTheme
import com.tmdb.shared_ui.home.HomeUiEvent
import com.tmdb.shared_ui.home.HomeUiEvent.NavigateBack
import com.tmdb.shared_ui.home.HomeUiEvent.OpenMovie
import com.tmdb.shared_ui.home.HomeUiEvent.ReloadMovieSection

@Composable
fun HomeScreen(homeViewModel: SharedHomeViewModel) {
    Tmdb_TestTheme {
        val data by homeViewModel.uiStateFlow().collectAsState(HomeUiData.INITIAL)

        val onEvent: (HomeUiEvent) -> Unit = { event ->
            when (event) {
                NavigateBack -> {
                }
                is OpenMovie -> {
                }
                is ReloadMovieSection -> homeViewModel.onReloadMovieSection(event.movieSection)
            }
        }
        HomeScreenUi(data, onEvent)
    }
}