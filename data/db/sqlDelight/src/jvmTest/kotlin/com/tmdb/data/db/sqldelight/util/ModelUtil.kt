package com.tmdb.data.db.sqldelight.util

import com.tmdb.data.db.sqldelight.Movie
import kotlinx.datetime.LocalDate

object ModelUtil {
    val movieEntity = Movie(
        id = 550,
        title = "Fight Club",
        vote_average = 7.8,
        release_date = LocalDate.parse("1999-10-12"),
        poster_url = null,
        now_playing = false,
        now_popular = false,
        top_rated = false,
        upcoming = false,
    )
}
