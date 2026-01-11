package com.tmdb.data.source.local.impl.mapping

import com.tmdb.data.db.sqldelight.Movie
import com.tmdb.data.model.movie.MovieDataModel

typealias MovieDataModelToEntityMapper = (input: MovieDataModel) -> Movie

fun movieDataModelToEntityMapperImpl(
    input: MovieDataModel,
): Movie = Movie(
    id = input.id?.toLong(),
    title = input.title,
    vote_average = input.voteAverage,
    release_date = input.releaseDate,
    poster_url = input.posterUrl,
    now_playing = false,
    now_popular = false,
    top_rated = false,
    upcoming = false,
)
