package com.tmdb.home

import androidx.compose.runtime.Composable
import com.tmdb.core.data.UiState
import com.tmdb.composeapp.generated.resources.Res
import com.tmdb.composeapp.generated.resources.failed_to_load
import com.tmdb.composeapp.generated.resources.reload
import com.tmdb.home.data.HomeUiData
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@Preview
@Composable
fun MovieSectionErrorPreview() {
    MovieSectionError(
        text = stringResource(Res.string.failed_to_load),
        buttonText = stringResource(Res.string.reload),
        onReloadSection = { }
    )
}

@Preview
@Composable
fun MovieSectionItemPreview() {
    MovieSectionItem(
        movie = HomeUiData.Movie(
            id = 1,
            title = "Movie 1",
            averageVote = 70.7,
            releaseDate = LocalDate.parse("1 Jan 2022"),
            posterUrl = null
        ),
        onMovieClick = { }
    )
}

@OptIn(ExperimentalTime::class)
private val previewDateToday = Clock.System.now().toLocalDateTime(TimeZone.UTC).date

private val moviesPreview = listOf(
    HomeUiData.Movie(
        id = 1,
        title = "Movie 1",
        averageVote = 70.7,
        releaseDate = previewDateToday,
        posterUrl = null
    ),
    HomeUiData.Movie(
        id = 2,
        title = "Movie 2",
        averageVote = 20.7,
        releaseDate = previewDateToday.minus(DatePeriod(years = 1)),
        posterUrl = null
    ),
    HomeUiData.Movie(
        id = 3,
        title = "Movie 3",
        averageVote = 95.7,
        releaseDate = previewDateToday.minus(DatePeriod(years = 2)),
        posterUrl = null
    )
)

@Preview
@Composable
fun MovieSectionListPreview() {
    MovieSectionList(movies = moviesPreview, onMovieClick = { })
}

@Preview
@Composable
fun MovieSectionLoadingStatePreview() {
    MovieSection(
        title = "Popular movies",
        sectionState = UiState.Loading(),
        onReloadSection = { },
        onMovieClick = { }
    )
}

@Preview
@Composable
fun MovieSectionErrorStatePreview() {
    MovieSection(
        title = "Popular movies",
        sectionState = UiState.Error(),
        onReloadSection = { },
        onMovieClick = { }
    )
}

@Preview
@Composable
fun MovieSectionNetworkErrorStatePreview() {
    MovieSection(
        title = "Popular movies",
        sectionState = UiState.NetworkError(),
        onReloadSection = { },
        onMovieClick = { }
    )
}

@Preview
@Composable
fun MovieSectionSuccessStatePreview() {
    MovieSection(
        title = "Popular movies",
        sectionState = UiState.Success(moviesPreview),
        onReloadSection = { },
        onMovieClick = { }
    )
}
