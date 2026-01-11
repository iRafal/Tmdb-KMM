package com.tmdb.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.request.ImageRequest
import com.tmdb.core.data.UiState
import com.tmdb.home.data.HomeUiData.Movie
import com.tmdb.resources.Res
import com.tmdb.resources.failed_to_load
import com.tmdb.resources.no_internet
import com.tmdb.resources.reload
import com.tmdb.utils.format
import org.jetbrains.compose.resources.stringResource

@Composable
fun MovieSectionError(text: String, buttonText: String, onReloadSection: () -> Unit) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = text, style = MaterialTheme.typography.bodyLarge)
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
        AsyncImage(
            modifier = Modifier
                .size(96.dp)
                .align(Alignment.CenterHorizontally),
            model = ImageRequest.Builder(LocalPlatformContext.current)
                .data(movie.posterUrl.orEmpty())
                .build(),
            placeholder = null,
            contentDescription = null,
        )

        Spacer(modifier = Modifier.height(8.dp))

        Column {
            Text(text = movie.title, style = MaterialTheme.typography.titleLarge)

            Spacer(modifier = Modifier.height(8.dp))

            movie.releaseDate?.let { releaseDate ->
                val formattedDate = releaseDate.format("d MMM yyyy")
                Text(text = formattedDate, style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(8.dp))
            }

            Text(text = movie.averageVote.toString(), style = MaterialTheme.typography.bodyMedium)
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
        Text(text = title, style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
            when (sectionState) {
                is UiState.Loading -> {
                    CircularProgressIndicator()
                }

                is UiState.Error -> {
                    MovieSectionError(
                        text = stringResource(Res.string.failed_to_load),
                        buttonText = stringResource(Res.string.reload),
                        onReloadSection = onReloadSection
                    )
                }

                is UiState.NetworkError -> {
                    MovieSectionError(
                        text = stringResource(Res.string.no_internet),
                        buttonText = stringResource(Res.string.reload),
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
