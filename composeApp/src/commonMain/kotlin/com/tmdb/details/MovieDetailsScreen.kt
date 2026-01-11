package com.tmdb.details

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun MovieDetailsScreen(
    navController: NavController,
    movieId: Int,
    viewModel: MovieDetailsViewModel = koinViewModel<MovieDetailsViewModel>(
        parameters = { parametersOf(movieId) },
    ),
) {
    val state = MovieDetailsUiState.Loading
//        val state by movieDetailsViewModel.state.collectAsState(MovieDetailsState.Idle)
    val onEvent: (MovieDetailsUiEvent) -> Unit = { event ->
        when (event) {
            MovieDetailsUiEvent.NavigateBack -> navController.popBackStack()
        }
    }
    MovieDetailsScreenUi(state, onEvent)
}
