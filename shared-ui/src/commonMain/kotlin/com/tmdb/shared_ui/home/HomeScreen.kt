package com.tmdb.shared_ui.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.tmdb.shared.home.HomeViewModel
import com.tmdb.shared_ui.home.HomeUiEvent.NavigateBack
import com.tmdb.shared_ui.home.HomeUiEvent.OpenMovie
import com.tmdb.shared_ui.home.HomeUiEvent.ReloadMovieSection
import com.tmdb.shared.home.data.HomeUiData
import com.tmdb.shared_ui.app.navigation.AppNavigation.MovieDetails
import com.tmdb.shared_ui.app.navigation.AppNavigation.Close
import com.tmdb.shared_ui.core.theme.Tmdb_TestTheme

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel,
    onNavigate: (route: String) -> Unit,
) {
    Tmdb_TestTheme {
        val data by homeViewModel.uiStateFlow.collectAsState(HomeUiData.INITIAL)

        val onEvent: (HomeUiEvent) -> Unit = { event ->
            when (event) {
                NavigateBack -> onNavigate(Close.route)
                is OpenMovie -> {
                    onNavigate(MovieDetails.getRouteNameWithArguments(event.id.toString()))
                }
                is ReloadMovieSection -> homeViewModel.onReloadMovieSection(event.movieSection)
            }
        }
        HomeScreenUi(data, onEvent)
    }
}