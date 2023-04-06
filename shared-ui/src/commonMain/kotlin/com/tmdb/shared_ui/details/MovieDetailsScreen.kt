package com.tmdb.shared_ui.details

import androidx.compose.runtime.Composable
import com.tmdb.shared.details.MovieDetailsViewModel
import com.tmdb.shared_ui.app.navigation.AppNavigation.Close
import com.tmdb.shared_ui.core.theme.Tmdb_TestTheme
import com.tmdb.shared_ui.details.MovieDetailsUiEvent.NavigateBack
import com.tmdb.shared_ui.details.MovieDetailsUiState.Loading

@Composable
fun MovieDetailsScreen(
    movieId: Int,
    movieDetailsViewModel: MovieDetailsViewModel,
    onNavigate: (route: String) -> Unit
) {
    Tmdb_TestTheme {
        val state = Loading
//        val state by movieDetailsViewModel.state.collectAsState(MovieDetailsState.Idle)
        val onEvent: (MovieDetailsUiEvent) -> Unit = { event ->
            when (event) {
                NavigateBack -> onNavigate(Close.route)
            }
        }
        MovieDetailsScreenUi(state, onEvent)
    }
}