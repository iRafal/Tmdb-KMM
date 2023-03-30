package com.tmdb.android.ui.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.tmdb.shared.details.data.MovieDetailsUiData
import com.tmdb.android.ui.core.theme.Tmdb_TestTheme
import com.tmdb.shared_ui.details.MovieDetailsUiEvent
import com.tmdb.shared_ui.details.MovieDetailsUiState


@Preview(showBackground = false, showSystemUi = false)
@ExperimentalMaterialApi
@Composable
fun MovieDetailsStateIdlePreview() {
    Tmdb_TestTheme {
        MovieDetailsScreenUi(
            MovieDetailsUiState.Idle,
            onEvent = { }
        )
    }
}

@Preview(showBackground = false, showSystemUi = false)
@ExperimentalMaterialApi
@Composable
fun MovieDetailsStateLoadingPreview() {
    Tmdb_TestTheme {
        MovieDetailsScreenUi(
            MovieDetailsUiState.Loading,
            onEvent = { }
        )
    }
}

@Preview(showBackground = false, showSystemUi = false)
@ExperimentalMaterialApi
@Composable
fun MovieDetailsStateErrorPreview() {
    Tmdb_TestTheme {
        MovieDetailsScreenUi(
            MovieDetailsUiState.Error(),
            onEvent = { }
        )
    }
}

@Preview(showBackground = false, showSystemUi = false)
@ExperimentalMaterialApi
@Composable
fun MovieDetailsStateNetworkErrorPreview() {
    Tmdb_TestTheme {
        MovieDetailsScreenUi(
            MovieDetailsUiState.NetworkError(),
            onEvent = { }
        )
    }
}

@Preview(showBackground = false, showSystemUi = false)
@ExperimentalMaterialApi
@Composable
fun MovieDetailsStateSuccessPreview() {
    Tmdb_TestTheme {
        val data = MovieDetailsUiData()
        MovieDetailsScreenUi(
            MovieDetailsUiState.Success(data),
            onEvent = { }
        )
    }
}

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