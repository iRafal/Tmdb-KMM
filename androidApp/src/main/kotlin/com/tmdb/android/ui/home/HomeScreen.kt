package com.tmdb.android.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.tmdb.android.ui.home.HomeUiEvent.NavigateBack
import com.tmdb.android.ui.home.HomeUiEvent.OpenMovie
import com.tmdb.android.ui.home.HomeUiEvent.ReloadMovieSection
import com.tmdb.shared.home.data.HomeUiData
import com.tmdb.android.ui.app.navigation.AppNavigation.Close
import com.tmdb.android.ui.app.navigation.AppNavigation.MovieDetails
import com.tmdb.android.ui.core.theme.Tmdb_TestTheme

@Composable
fun HomeScreen(
    navController: NavController,
    homeViewModel: HomeViewModel = hiltViewModel(),
) {
    Tmdb_TestTheme {
        val data by homeViewModel.uiStateFlow.collectAsState(HomeUiData.INITIAL)

        val onEvent: (HomeUiEvent) -> Unit = { event ->
            when (event) {
                NavigateBack -> navController.navigate(Close.route)
                is OpenMovie -> {
                    navController.navigate(
                        MovieDetails.getRouteNameWithArguments(
                            event.id.toString()
                        )
                    )
                }
                is ReloadMovieSection -> homeViewModel.onReloadMovieSection(event.movieSection)
            }
        }
        HomeScreenUi(data, onEvent)
    }
}