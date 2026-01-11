package com.tmdb.home

import androidx.compose.runtime.Composable
import com.tmdb.core.data.UiState
import com.tmdb.core.theme.Tmdb_TestTheme
import com.tmdb.home.data.HomeMovieSection
import com.tmdb.home.data.HomeUiData
import com.tmdb.home.data.HomeUiData.Movie
import kotlinx.datetime.LocalDate
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun HomeAllSectionsLoadingPreview() {
    Tmdb_TestTheme {
        val data = HomeUiData(
            mapOf(
                HomeMovieSection.NOW_PLAYING to UiState.Loading(),
                HomeMovieSection.NOW_POPULAR to UiState.Loading(),
                HomeMovieSection.TOP_RATED to UiState.Loading(),
                HomeMovieSection.UPCOMING to UiState.Loading(),
            ),
        )
        HomeScreenUi(
            data,
            onEvent = { },
        )
    }
}

@Preview
@Composable
fun HomeAllSectionsErrorPreview() {
    Tmdb_TestTheme {
        val data = HomeUiData(
            mapOf(
                HomeMovieSection.NOW_PLAYING to UiState.Error(),
                HomeMovieSection.NOW_POPULAR to UiState.Error(),
                HomeMovieSection.TOP_RATED to UiState.Error(),
                HomeMovieSection.UPCOMING to UiState.Error(),
            ),
        )
        HomeScreenUi(
            data,
            onEvent = { },
        )
    }
}

@Preview
@Composable
fun HomeAllSectionsNetworkErrorPreview() {
    Tmdb_TestTheme {
        val data = HomeUiData(
            mapOf(
                HomeMovieSection.NOW_PLAYING to UiState.NetworkError(),
                HomeMovieSection.NOW_POPULAR to UiState.NetworkError(),
                HomeMovieSection.TOP_RATED to UiState.NetworkError(),
                HomeMovieSection.UPCOMING to UiState.NetworkError(),
            ),
        )
        HomeScreenUi(
            data,
            onEvent = { },
        )
    }
}

@Preview
@Composable
fun HomeStateSuccessPreview() {
    Tmdb_TestTheme {
        val movies = listOf(
            Movie(
                id = 1,
                title = "Movie 1",
                averageVote = 70.7,
                releaseDate = LocalDate.parse("1 Jan 2022"),
                posterUrl = null,
            ),
            Movie(
                id = 2,
                title = "Movie 2",
                averageVote = 20.7,
                releaseDate = LocalDate.parse("1 Jan 2020"),
                posterUrl = null,
            ),
            Movie(
                id = 3,
                title = "Movie 3",
                averageVote = 95.7,
                releaseDate = LocalDate.parse("1 Jan 2021"),
                posterUrl = null,
            ),
        )
        val data = HomeUiData(
            mapOf(
                HomeMovieSection.NOW_PLAYING to UiState.Success(movies),
                HomeMovieSection.NOW_POPULAR to UiState.Success(movies),
                HomeMovieSection.TOP_RATED to UiState.Success(movies),
                HomeMovieSection.UPCOMING to UiState.Success(movies),
            ),
        )
        HomeScreenUi(
            data,
            onEvent = { },
        )
    }
}

@Preview
@Composable
fun HomeMixedStatesPreview() {
    Tmdb_TestTheme {
        val movies = listOf(
            Movie(
                id = 1,
                title = "Movie 1",
                averageVote = 70.7,
                releaseDate = LocalDate.parse("1 Jan 2022"),
                posterUrl = null,
            ),
            Movie(
                id = 2,
                title = "Movie 2",
                averageVote = 20.7,
                releaseDate = LocalDate.parse("1 Jan 2020"),
                posterUrl = null,
            ),
            Movie(
                id = 3,
                title = "Movie 3",
                averageVote = 95.7,
                releaseDate = LocalDate.parse("1 Jan 2021"),
                posterUrl = null,
            ),
        )
        val data = HomeUiData(
            mapOf(
                HomeMovieSection.NOW_PLAYING to UiState.Success(movies),
                HomeMovieSection.NOW_POPULAR to UiState.NetworkError(),
                HomeMovieSection.TOP_RATED to UiState.Error(),
                HomeMovieSection.UPCOMING to UiState.Loading(),
            ),
        )
        HomeScreenUi(
            data,
            onEvent = { },
        )
    }
}

@Composable
fun Movie(id: Int, title: String, averageVote: Double, releaseDate: LocalDate, posterUrl: Nothing?) {
    TODO("Not yet implemented")
}
