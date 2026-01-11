package com.tmdb.data.source.local.impl.mapping

import com.tmdb.data.db.sqldelight.Movie
import com.tmdb.data.model.movie.MovieDataModel

typealias MovieEntityToDataModelMapper = (input: Movie) -> MovieDataModel

fun movieEntityToDataModelMapperImpl(
    input: Movie,
): MovieDataModel = MovieDataModel(
    id = input.id?.toInt(),
    title = input.title,
    voteAverage = input.vote_average,
    releaseDate = input.release_date,
    posterUrl = input.poster_url,
)
