package com.tmdb.data.source.remote.impl.genre

import com.tmdb.data.api.impl.genre.GenreApi
import com.tmdb.data.api.model.genre.Genre
import com.tmdb.data.api.model.util.ApiResponse
import com.tmdb.data.api.model.util.NetworkErrorModel
import com.tmdb.data.source.remote.contract.genre.GenreRemoteDataSource


class GenreRemoteDataSourceImpl(
    private val api: GenreApi
) : GenreRemoteDataSource {
    override suspend fun genreMovieList(language: String?): ApiResponse<List<Genre>, NetworkErrorModel> =
        api.genreMovieList(language)

    override suspend fun genreTvList(language: String?): ApiResponse<List<Genre>, NetworkErrorModel> =
        api.genreTvList(language)
}