package com.tmdb.shared.details

import androidx.compose.runtime.Composable
import com.tmdb.kmm.shared.ui.core.theme.Tmdb_TestTheme
import com.tmdb.shared.app.navigation.AppNavigation
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MovieDetailsScreen(
    movieId: Int,
    movieDetailsViewModel: MovieDetailsViewModel = koinViewModel<MovieDetailsViewModel>(),
    onNavigate: (route: String) -> Unit
) {
    Tmdb_TestTheme {
        val state = MovieDetailsUiState.Loading
//        val state by movieDetailsViewModel.state.collectAsState(MovieDetailsState.Idle)
        val onEvent: (MovieDetailsUiEvent) -> Unit = { event ->
            when (event) {
                MovieDetailsUiEvent.NavigateBack -> onNavigate(AppNavigation.Close.route)
            }
        }
        MovieDetailsScreenUi(state, onEvent)
    }
}
