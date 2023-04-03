package com.tmdb.shared_ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tmdb.shared.core.data.UiState
import com.tmdb.shared.home.data.HomeUiData.Movie
import com.tmdb.shared_ui.MR
import com.tmdb.shared_ui.core.compose.RemoteImage
import com.tmdb.shared_ui.utils.format
import dev.icerock.moko.resources.compose.stringResource


@Composable
fun MovieSectionError(text: String, buttonText: String, onReloadSection: () -> Unit) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = text, style = MaterialTheme.typography.body1)
        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = { onReloadSection() }) {
            Text(text = buttonText)
        }
    }
}

@Composable
fun MovieSectionItem(
    movie: Movie,
    onMovieClick: (movieId: Int) -> Unit
) {
    Column(
        modifier = Modifier.clickable { onMovieClick(movie.id) }
    ) {
        RemoteImage(
            imageUrl = movie.posterUrl.orEmpty(),
            modifier = Modifier
                .size(96.dp)
                .align(Alignment.CenterHorizontally),
            placeholder = null,
            contentDescription = null
        )

        Spacer(modifier = Modifier.height(8.dp))

        Column {
            Text(text = movie.title, style = MaterialTheme.typography.body2)

            Spacer(modifier = Modifier.height(8.dp))

            movie.releaseDate?.let { releaseDate ->
                val formattedDate = releaseDate.format("d MMM yyyy")
                Text(text = formattedDate, style = MaterialTheme.typography.caption)
                Spacer(modifier = Modifier.height(8.dp))
            }

            Text(text = movie.averageVote.toString(), style = MaterialTheme.typography.caption)
        }
    }
}

@Composable
fun MovieSectionList(movies: List<Movie>, onMovieClick: (movieId: Int) -> Unit) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        content = {
            movies.forEach { movie ->
                item(key = movie.id) {
                    MovieSectionItem(movie, onMovieClick)
                }
            }
        }
    )
}

@Composable
fun MovieSection(
    title: String,
    sectionState: UiState<List<Movie>>,
    onReloadSection: () -> Unit,
    onMovieClick: (movieId: Int) -> Unit
) {
    Column {
        Text(text = title, style = MaterialTheme.typography.h6)

        Spacer(modifier = Modifier.height(16.dp))

        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
            when (sectionState) {
                is UiState.Loading -> {
                    CircularProgressIndicator()
                }
                is UiState.Error -> {
                    MovieSectionError(
                        text = stringResource(MR.strings.failed_to_load),
                        buttonText = stringResource(MR.strings.reload),
                        onReloadSection = onReloadSection
                    )
                }
                is UiState.NetworkError -> {
                    MovieSectionError(
                        text = stringResource(MR.strings.no_internet),
                        buttonText = stringResource(MR.strings.reload),
                        onReloadSection = onReloadSection
                    )
                }
                is UiState.Success<List<Movie>> -> {
                    MovieSectionList(sectionState.data, onMovieClick)
                }
            }
        }
    }
}