package com.tmdb.data.api.model.genre

import com.tmdb.data.api.model.genre.Genre
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenresList(@SerialName("genres") val genres: List<Genre> = listOf())
