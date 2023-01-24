package com.tmdb.data.api.model.movie

import com.tmdb.data.api.model.util.serializer.MovieStatusSerializer
import kotlinx.serialization.Serializable

@Serializable(with = MovieStatusSerializer::class)
enum class MovieStatus {
     RUMORED,
     PLANNED,
     IN_PRODUCTION,
     POST_PRODUCTION,
     RELEASED,
     CANCELED
}

