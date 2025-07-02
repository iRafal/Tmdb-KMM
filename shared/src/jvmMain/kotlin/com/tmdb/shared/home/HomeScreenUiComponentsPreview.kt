package com.tmdb.shared.home

import androidx.compose.runtime.Composable
import com.tmdb.shared.core.data.UiState
import com.tmdb.shared.home.data.HomeUiData.Movie
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.LocalDate
import kotlinx.datetime.minus
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import tmdb_kmm.shared.generated.resources.Res
import tmdb_kmm.shared.generated.resources.failed_to_load
import tmdb_kmm.shared.generated.resources.reload
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
        movie = Movie(
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
private val previewDateToday = Clock.System.now().toLocalDateTime(kotlinx.datetime.TimeZone.UTC).date

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
