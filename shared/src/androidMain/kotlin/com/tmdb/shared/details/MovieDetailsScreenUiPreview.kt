package com.tmdb.shared.details

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.tmdb.shared.core.theme.Tmdb_TestTheme
import com.tmdb.shared.details.data.MovieDetailsUiData

@Preview(showBackground = false, showSystemUi = false)
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
