package com.tmdb.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.tmdb.core.data.UiState
import com.tmdb.home.data.HomeUiData
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun MovieSectionErrorPreview() {
    MovieSectionError(
        text = "Failed to load",
        buttonText = "Reload",
        onReloadSection = { },
    )
}

@OptIn(ExperimentalTime::class)
private val previewDateToday = Clock.System.now().toLocalDateTime(TimeZone.UTC).date

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun MovieSectionItemPreview() {
    MovieSectionItem(
        movie = HomeUiData.Movie(
            id = 1, title = "Movie 1", averageVote = 70.7,
            releaseDate = previewDateToday,
            posterUrl = null,
        ),
        onMovieClick = { },
    )
}

private val moviesPreview = listOf(
    HomeUiData.Movie(
        id = 1,
        title = "Movie 1",
        averageVote = 70.7,
        releaseDate = previewDateToday,
        posterUrl = null,
    ),
    HomeUiData.Movie(
        id = 2,
        title = "Movie 2",
        averageVote = 20.7,
        releaseDate = previewDateToday.minus(DatePeriod(years = 1)),
        posterUrl = null,
    ),
    HomeUiData.Movie(
        id = 3,
        title = "Movie 3",
        averageVote = 95.7,
        releaseDate = previewDateToday.minus(DatePeriod(years = 2)),
        posterUrl = null,
    ),
)

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun MovieSectionListPreview() {
    MovieSectionList(movies = moviesPreview, onMovieClick = { })
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun MovieSectionLoadingStatePreview() {
    MovieSection(
        title = "Popular movies",
        sectionState = UiState.Loading(),
        onReloadSection = { },
        onMovieClick = { },
    )
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun MovieSectionErrorStatePreview() {
    MovieSection(
        title = "Popular movies",
        sectionState = UiState.Error(),
        onReloadSection = { },
        onMovieClick = { },
    )
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun MovieSectionNetworkErrorStatePreview() {
    MovieSection(
        title = "Popular movies",
        sectionState = UiState.NetworkError(),
        onReloadSection = { },
        onMovieClick = { },
    )
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun MovieSectionSuccessStatePreview() {
    MovieSection(
        title = "Popular movies",
        sectionState = UiState.Success(moviesPreview),
        onReloadSection = { },
        onMovieClick = { },
    )
}
