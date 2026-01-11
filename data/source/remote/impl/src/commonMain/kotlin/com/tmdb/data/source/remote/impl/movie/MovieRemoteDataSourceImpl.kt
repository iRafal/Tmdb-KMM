package com.tmdb.data.source.remote.impl.movie

import com.tmdb.data.api.impl.movie.MovieApi
import com.tmdb.data.api.model.data.DataPage
import com.tmdb.data.api.model.movie.Movie
import com.tmdb.data.api.model.util.ApiResponse
import com.tmdb.data.api.model.util.NetworkErrorModel
import com.tmdb.data.source.remote.contract.movie.MovieRemoteDataSource

class MovieRemoteDataSourceImpl(
    private val api: MovieApi,
) : MovieRemoteDataSource {
    override suspend fun movie(
        movieId: Int,
        language: String?,
        appendToResponse: String?,
    ): ApiResponse<Movie, NetworkErrorModel> = api.movie(movieId, language, appendToResponse)

    override suspend fun latestMovie(
        language: String?,
    ): ApiResponse<Movie, NetworkErrorModel> = api.latestMovie(language)

    override suspend fun nowPlayingMovies(
        language: String?,
        page: Int?,
        region: String?,
    ): ApiResponse<DataPage<Movie>, NetworkErrorModel> =
        api.nowPlayingMovies(language, page, region)

    override suspend fun nowPopularMovies(
        language: String?,
        page: Int?,
        region: String?,
    ): ApiResponse<DataPage<Movie>, NetworkErrorModel> =
        api.nowPopularMovies(language, page, region)

    override suspend fun topRatedMovies(
        language: String?,
        page: Int?,
        region: String?,
    ): ApiResponse<DataPage<Movie>, NetworkErrorModel> = api.topRatedMovies(language, page, region)

    override suspend fun upcomingMovies(
        language: String?,
        page: Int?,
        region: String?,
    ): ApiResponse<DataPage<Movie>, NetworkErrorModel> = api.upcomingMovies(language, page, region)
}
