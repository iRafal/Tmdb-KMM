package com.tmdb.shared.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.tmdb.shared.home.data.HomeUiData
import com.tmdb.kmm.shared.ui.core.theme.Tmdb_TestTheme
import com.tmdb.shared.app.navigation.AppNavigation
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = koinViewModel<HomeViewModel>(),
    onNavigate: (route: String) -> Unit,
) {
    Tmdb_TestTheme {
        val data by homeViewModel.uiStateFlow.collectAsState(HomeUiData.INITIAL)

        val onEvent: (HomeUiEvent) -> Unit = { event ->
            when (event) {
                HomeUiEvent.NavigateBack -> onNavigate(AppNavigation.Close.route)
                is HomeUiEvent.OpenMovie -> {
                    onNavigate(AppNavigation.MovieDetails.getRouteNameWithArguments(event.id.toString()))
                }

                is HomeUiEvent.ReloadMovieSection -> {
                    homeViewModel.onReloadMovieSection(event.movieSection)
                }
            }
        }
        HomeScreenUi(data, onEvent)
    }
}
