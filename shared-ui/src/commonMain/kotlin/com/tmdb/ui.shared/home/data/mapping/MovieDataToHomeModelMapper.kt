package com.tmdb.ui.shared.home.data.mapping

import com.tmdb.ui.shared.home.data.HomeUiData
import com.tmdb.data.model.movie.MovieDataModel

typealias MovieDataToHomeModelMapper = (input: MovieDataModel) -> HomeUiData.Movie

fun movieDataToHomeModelMapperImpl(): MovieDataToHomeModelMapper = { input ->
    HomeUiData.Movie(
        id = checkNotNull(input.id),
        title = checkNotNull(input.title),
        averageVote = input.voteAverage ?: 0.0,
        releaseDate = input.releaseDate,
        posterUrl = input.posterUrl
    )
}