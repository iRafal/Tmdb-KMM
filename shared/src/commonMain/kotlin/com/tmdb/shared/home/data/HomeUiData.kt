package com.tmdb.shared.home.data

import com.tmdb.shared.home.data.HomeMovieSection
import com.tmdb.shared.core.data.UiState
import com.tmdb.shared.core.data.UiState.Loading
import com.tmdb.shared.home.data.HomeMovieSection.NOW_PLAYING
import com.tmdb.shared.home.data.HomeMovieSection.NOW_POPULAR
import com.tmdb.shared.home.data.HomeMovieSection.TOP_RATED
import com.tmdb.shared.home.data.HomeMovieSection.UPCOMING
import kotlinx.datetime.LocalDate

data class HomeUiData(val movieSections: Map<HomeMovieSection, UiState<List<Movie>>>) {
    data class Movie(
        val id: Int,
        val title: String,
        val averageVote: Double,
        val releaseDate: LocalDate?,
        val posterUrl: String?
    )

    companion object {
        val INITIAL = HomeUiData(
            movieSections = mapOf(
                NOW_PLAYING to Loading(),
                NOW_POPULAR to Loading(),
                TOP_RATED to Loading(),
                UPCOMING to Loading(),
            )
        )
    }
}

