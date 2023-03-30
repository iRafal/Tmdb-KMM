package com.tmdb.android.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tmdb.android.ui.core.compose.ScrollableColumn
import com.tmdb.android.ui.core.theme.Tmdb_TestTheme
import com.tmdb.android.ui.home.HomeUiEvent.OpenMovie
import com.tmdb.android.ui.home.HomeUiEvent.ReloadMovieSection
import com.tmdb.shared.core.data.UiState
import com.tmdb.shared.home.data.HomeMovieSection
import com.tmdb.shared.home.data.HomeMovieSection.NOW_PLAYING
import com.tmdb.shared.home.data.HomeMovieSection.NOW_POPULAR
import com.tmdb.shared.home.data.HomeMovieSection.TOP_RATED
import com.tmdb.shared.home.data.HomeMovieSection.UPCOMING
import com.tmdb.shared.home.data.HomeUiData
import com.tmdb.shared.home.data.HomeUiData.Movie
import kotlinx.datetime.LocalDate


@Preview(showBackground = false, showSystemUi = false)
@ExperimentalMaterialApi
@Composable
fun HomeAllSectionsLoadingPreview() {
    Tmdb_TestTheme {
        val data = HomeUiData(
            mapOf(
                NOW_PLAYING to UiState.Loading(),
                NOW_POPULAR to UiState.Loading(),
                TOP_RATED to UiState.Loading(),
                UPCOMING to UiState.Loading(),
            )
        )
        HomeScreenUi(
            data,
            onEvent = { }
        )
    }
}

@Preview(showBackground = false, showSystemUi = false)
@ExperimentalMaterialApi
@Composable
fun HomeAllSectionsErrorPreview() {
    Tmdb_TestTheme {
        val data = HomeUiData(
            mapOf(
                NOW_PLAYING to UiState.Error(),
                NOW_POPULAR to UiState.Error(),
                TOP_RATED to UiState.Error(),
                UPCOMING to UiState.Error(),
            )
        )
        HomeScreenUi(
            data,
            onEvent = { }
        )
    }
}

@Preview(showBackground = false, showSystemUi = false)
@ExperimentalMaterialApi
@Composable
fun HomeAllSectionsNetworkErrorPreview() {
    Tmdb_TestTheme {
        val data = HomeUiData(
            mapOf(
                NOW_PLAYING to UiState.NetworkError(),
                NOW_POPULAR to UiState.NetworkError(),
                TOP_RATED to UiState.NetworkError(),
                UPCOMING to UiState.NetworkError(),
            )
        )
        HomeScreenUi(
            data,
            onEvent = { }
        )
    }
}

@Preview(showBackground = false, showSystemUi = false)
@ExperimentalMaterialApi
@Composable
fun HomeStateSuccessPreview() {
    Tmdb_TestTheme {
        val movies = listOf(
            Movie(
                id = 1,
                title = "Movie 1",
                averageVote = 70.7,
                releaseDate = LocalDate.parse("1 Jan 2022"),
                posterUrl = null
            ),
            Movie(
                id = 2,
                title = "Movie 2",
                averageVote = 20.7,
                releaseDate = LocalDate.parse("1 Jan 2020"),
                posterUrl = null
            ),
            Movie(
                id = 3,
                title = "Movie 3",
                averageVote = 95.7,
                releaseDate = LocalDate.parse("1 Jan 2021"),
                posterUrl = null
            )
        )
        val data = HomeUiData(
            mapOf(
                NOW_PLAYING to UiState.Success(movies),
                NOW_POPULAR to UiState.Success(movies),
                TOP_RATED to UiState.Success(movies),
                UPCOMING to UiState.Success(movies),
            )
        )
        HomeScreenUi(
            data,
            onEvent = { }
        )
    }
}

@Preview(showBackground = false, showSystemUi = false)
@ExperimentalMaterialApi
@Composable
fun HomeMixedStatesPreview() {
    Tmdb_TestTheme {
        val movies = listOf(
            Movie(
                id = 1,
                title = "Movie 1",
                averageVote = 70.7,
                releaseDate = LocalDate.parse("1 Jan 2022"),
                posterUrl = null
            ),
            Movie(
                id = 2,
                title = "Movie 2",
                averageVote = 20.7,
                releaseDate = LocalDate.parse("1 Jan 2020"),
                posterUrl = null
            ),
            Movie(
                id = 3,
                title = "Movie 3",
                averageVote = 95.7,
                releaseDate = LocalDate.parse("1 Jan 2021"),
                posterUrl = null
            )
        )
        val data = HomeUiData(
            mapOf(
                NOW_PLAYING to UiState.Success(movies),
                NOW_POPULAR to UiState.NetworkError(),
                TOP_RATED to UiState.Error(),
                UPCOMING to UiState.Loading(),
            )
        )
        HomeScreenUi(
            data,
            onEvent = { }
        )
    }
}

@Composable
fun HomeScreenUi(
    data: HomeUiData,
    onEvent: (HomeUiEvent) -> Unit
) {
    Home(
        data,
        onReloadSection = { section -> onEvent(ReloadMovieSection(section)) },
        onMovieClick = { movieId -> onEvent(OpenMovie(movieId)) }
    )
}

@Composable
fun Home(
    data: HomeUiData,
    onReloadSection: (HomeMovieSection) -> Unit,
    onMovieClick: (movieId: Int) -> Unit
) {
    Scaffold(
        content = {
            Box(modifier = Modifier.padding(it)) {
                HomeContent(data, onReloadSection, onMovieClick)
            }
        }
    )
}

@Composable
fun HomeContent(
    data: HomeUiData,
    onReloadSection: (HomeMovieSection) -> Unit,
    onMovieClick: (movieId: Int) -> Unit
) {
    ScrollableColumn(
        Modifier.padding(horizontal = 16.dp, vertical = 32.dp)
    ) {
        data.movieSections.forEach { (section, sectionState) ->
            MovieSection(
                title = section.sectionUiName,
                sectionState = sectionState,
                onReloadSection = { onReloadSection(section) },
                onMovieClick = onMovieClick
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

private val HomeMovieSection.sectionUiName: String
    get() = when (this) {
        HomeMovieSection.NOW_PLAYING -> "Now Playing"
        HomeMovieSection.NOW_POPULAR -> "Now Popular"
        HomeMovieSection.TOP_RATED -> "Top Rated"
        HomeMovieSection.UPCOMING -> "Upcoming"
    }
