package com.tmdb.com.tmdb.details

import androidx.compose.runtime.Composable
import com.tmdb.core.theme.Tmdb_TestTheme
import com.tmdb.details.MovieDetailsScreenUi
import com.tmdb.details.MovieDetailsUiState
import com.tmdb.details.data.MovieDetailsUiData
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun MovieDetailsStateIdlePreview() {
    Tmdb_TestTheme {
        MovieDetailsScreenUi(
            MovieDetailsUiState.Idle,
            onEvent = { }
        )
    }
}

@Preview
@Composable
fun MovieDetailsStateLoadingPreview() {
    Tmdb_TestTheme {
        MovieDetailsScreenUi(
            MovieDetailsUiState.Loading,
            onEvent = { }
        )
    }
}

@Preview
@Composable
fun MovieDetailsStateErrorPreview() {
    Tmdb_TestTheme {
        MovieDetailsScreenUi(
            MovieDetailsUiState.Error(),
            onEvent = { }
        )
    }
}

@Preview
@Composable
fun MovieDetailsStateNetworkErrorPreview() {
    Tmdb_TestTheme {
        MovieDetailsScreenUi(
            MovieDetailsUiState.NetworkError(),
            onEvent = { }
        )
    }
}

@Preview
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
