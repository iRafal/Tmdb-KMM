package com.tmdb.shared.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tmdb.shared.home.data.HomeMovieSection
import com.tmdb.shared.home.data.HomeUiData
import com.tmdb.shared.core.compose.ScrollableColumn
import org.jetbrains.compose.resources.stringResource
import tmdb_kmm.shared.generated.resources.Res
import tmdb_kmm.shared.generated.resources.now_playing
import tmdb_kmm.shared.generated.resources.now_popular
import tmdb_kmm.shared.generated.resources.top_rated
import tmdb_kmm.shared.generated.resources.upcoming

@Composable
fun HomeScreenUi(
    data: HomeUiData,
    onEvent: (HomeUiEvent) -> Unit
) {
    Home(
        data,
        onReloadSection = { section -> onEvent(HomeUiEvent.ReloadMovieSection(section)) },
        onMovieClick = { movieId -> onEvent(HomeUiEvent.OpenMovie(movieId)) }
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
    @Composable
    get() = when (this) {
        HomeMovieSection.NOW_PLAYING -> Res.string.now_playing
        HomeMovieSection.NOW_POPULAR -> Res.string.now_popular
        HomeMovieSection.TOP_RATED -> Res.string.top_rated
        HomeMovieSection.UPCOMING -> Res.string.upcoming
    }.run { stringResource(this) }
