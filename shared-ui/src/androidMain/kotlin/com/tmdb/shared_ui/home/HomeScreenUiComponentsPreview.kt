package com.tmdb.shared_ui.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.tmdb.shared.core.data.UiState
import com.tmdb.shared.home.data.HomeUiData.Movie
import kotlinx.datetime.Clock.System
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.toLocalDateTime

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun MovieSectionErrorPreview() {
    MovieSectionError(
        text = "Failed to load",
        buttonText = "Reload",
        onReloadSection = { }
    )
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun MovieSectionItemPreview() {
    MovieSectionItem(
        movie = Movie(
            id = 1, title = "Movie 1", averageVote = 70.7,
            releaseDate = LocalDate.parse("1 Jan 2022"),
            posterUrl = null
        ),
        onMovieClick = { }
    )
}

private val previewDateToday = System.now().toLocalDateTime(TimeZone.UTC).date

private val moviesPreview = listOf(
    Movie(
        id = 1,
        title = "Movie 1",
        averageVote = 70.7,
        releaseDate = previewDateToday,
        posterUrl = null
    ),
    Movie(
        id = 2,
        title = "Movie 2",
        averageVote = 20.7,
        releaseDate = previewDateToday.minus(DatePeriod(years = 1)),
        posterUrl = null
    ),
    Movie(
        id = 3,
        title = "Movie 3",
        averageVote = 95.7,
        releaseDate = previewDateToday.minus(DatePeriod(years = 2)),
        posterUrl = null
    )
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
        onMovieClick = { }
    )
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun MovieSectionErrorStatePreview() {
    MovieSection(
        title = "Popular movies",
        sectionState = UiState.Error(),
        onReloadSection = { },
        onMovieClick = { }
    )
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun MovieSectionNetworkErrorStatePreview() {
    MovieSection(
        title = "Popular movies",
        sectionState = UiState.NetworkError(),
        onReloadSection = { },
        onMovieClick = { }
    )
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun MovieSectionSuccessStatePreview() {
    MovieSection(
        title = "Popular movies",
        sectionState = UiState.Success(moviesPreview),
        onReloadSection = { },
        onMovieClick = { }
    )
}