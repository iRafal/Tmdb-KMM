package com.tmdb.shared.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.tmdb.shared.details.data.MovieDetailsUiData

@Composable
fun MovieDetailsScreenUi(
    state: MovieDetailsUiState,
    onEvent: (MovieDetailsUiEvent) -> Unit
) {
    MovieDetails(state)
}

@Composable
fun MovieDetails(state: MovieDetailsUiState) {
    Scaffold {
        Box(modifier = Modifier.padding(it)) {
            when (state) {
                MovieDetailsUiState.Idle, MovieDetailsUiState.Loading -> MovieDetailsLoading()
                is MovieDetailsUiState.Error -> MovieDetailsError()
                is MovieDetailsUiState.NetworkError -> MovieDetailsNetworkError()
                is MovieDetailsUiState.Success -> MovieDetailsContent(state.data)
            }
        }
    }
}

@Composable
fun MovieDetailsContent(data: MovieDetailsUiData) {
}

@Composable
fun MovieDetailsLoading() {
}

@Composable
fun MovieDetailsError() {
}

@Composable
fun MovieDetailsNetworkError() {
}
