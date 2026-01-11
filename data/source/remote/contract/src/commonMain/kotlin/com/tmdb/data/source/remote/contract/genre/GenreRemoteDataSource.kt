package com.tmdb.data.source.remote.contract.genre

import com.tmdb.data.api.model.genre.Genre
import com.tmdb.data.api.model.util.ApiResponse
import com.tmdb.data.api.model.util.NetworkErrorModel

interface GenreRemoteDataSource {

    suspend fun genreMovieList(language: String? = null): ApiResponse<List<Genre>, NetworkErrorModel>

    suspend fun genreTvList(language: String? = null): ApiResponse<List<Genre>, NetworkErrorModel>
}
