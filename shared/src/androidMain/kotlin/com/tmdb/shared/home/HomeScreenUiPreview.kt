package com.tmdb.shared.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.tmdb.kmm.shared.ui.core.theme.Tmdb_TestTheme
import com.tmdb.shared.core.data.UiState
import com.tmdb.shared.home.data.HomeMovieSection
import com.tmdb.shared.home.data.HomeUiData
import com.tmdb.shared.home.data.HomeUiData.Movie
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@Preview(showBackground = false, showSystemUi = false)
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

@Preview(showBackground = false, showSystemUi = false)
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

@Preview(showBackground = false, showSystemUi = false)
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

@OptIn(ExperimentalTime::class)
private val previewDateToday = Clock.System.now().toLocalDateTime(TimeZone.UTC).date

@Preview(showBackground = false, showSystemUi = false)
@Composable
fun HomeStateSuccessPreview() {
    Tmdb_TestTheme {
        val movies = listOf(
            Movie(
                id = 1,
                title = "Movie 1",
                averageVote = 70.7,
                releaseDate = previewDateToday,
                posterUrl = null,
            ),
            Movie(
                id = 2,
                title = "Movie 2",
                averageVote = 20.7,
                releaseDate = previewDateToday.plus(DatePeriod(years = 1)),
                posterUrl = null,
            ),
            Movie(
                id = 3,
                title = "Movie 3",
                averageVote = 95.7,
                releaseDate = previewDateToday.plus(DatePeriod(years = 2)),
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

@Preview(showBackground = false, showSystemUi = false)
@Composable
fun HomeMixedStatesPreview() {
    Tmdb_TestTheme {
        val movies = listOf(
            Movie(
                id = 1,
                title = "Movie 1",
                averageVote = 70.7,
                releaseDate = previewDateToday,
                posterUrl = null,
            ),
            Movie(
                id = 2,
                title = "Movie 2",
                averageVote = 20.7,
                releaseDate = previewDateToday.plus(DatePeriod(years = 21)),
                posterUrl = null,
            ),
            Movie(
                id = 3,
                title = "Movie 3",
                averageVote = 95.7,
                releaseDate = previewDateToday.plus(DatePeriod(years = 2)),
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
