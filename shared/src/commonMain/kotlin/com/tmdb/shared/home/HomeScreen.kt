package com.tmdb.shared.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import com.tmdb.shared.app.navigation.AppNavigation
import com.tmdb.shared.home.data.HomeUiData
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = koinViewModel<HomeViewModel>(),
    navController: NavController,
) {
    val data by homeViewModel.uiStateFlow.collectAsState(HomeUiData.INITIAL)

    val onEvent: (HomeUiEvent) -> Unit = { event ->
        when (event) {
            HomeUiEvent.NavigateBack -> navController.popBackStack()
            is HomeUiEvent.OpenMovie -> {
                navController.navigate(AppNavigation.MovieDetails.getRouteNameWithArguments(event.id.toString()))
            }

            is HomeUiEvent.ReloadMovieSection -> homeViewModel.onReloadMovieSection(event.movieSection)
        }
    }
    HomeScreenUi(data, onEvent)
}
