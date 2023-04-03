package com.tmdb.shared_ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tmdb.shared.home.data.HomeMovieSection
import com.tmdb.shared.home.data.HomeUiData
import com.tmdb.shared_ui.MR
import com.tmdb.shared_ui.core.compose.ScrollableColumn
import com.tmdb.shared_ui.home.HomeUiEvent.OpenMovie
import com.tmdb.shared_ui.home.HomeUiEvent.ReloadMovieSection
import dev.icerock.moko.resources.compose.stringResource

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
    @Composable
    get() = when (this) {
        HomeMovieSection.NOW_PLAYING -> MR.strings.now_playing
        HomeMovieSection.NOW_POPULAR -> MR.strings.now_popular
        HomeMovieSection.TOP_RATED -> MR.strings.top_rated
        HomeMovieSection.UPCOMING -> MR.strings.upcoming
    }.run { stringResource(this) }
