package com.tmdb.data.api.impl.genre

import com.tmdb.data.api.model.genre.Genre
import com.tmdb.data.api.model.util.ApiResponse
import com.tmdb.data.api.model.util.NetworkErrorModel

interface GenreApi {

    suspend fun genreMovieList(
        /**
         * Pass a ISO 639-1 value to display translated data for the fields that support it.
         * minLength: 2
         * pattern: ([a-z]{2})-([A-Z]{2})
         * default: en-US
         */
        language: String? = null,
    ): ApiResponse<List<Genre>, NetworkErrorModel>

    suspend fun genreTvList(
        /**
         * Pass a ISO 639-1 value to display translated data for the fields that support it.
         * minLength: 2
         * pattern: ([a-z]{2})-([A-Z]{2})
         * default: en-US
         */
        language: String? = null,
    ): ApiResponse<List<Genre>, NetworkErrorModel>
}
