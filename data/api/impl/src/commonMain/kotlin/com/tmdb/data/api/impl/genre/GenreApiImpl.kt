package com.tmdb.data.api.impl.genre

import com.tmdb.data.api.config.url.provider.genre.GenreUrlProvider
import com.tmdb.data.api.impl.util.runApiCall
import com.tmdb.data.api.model.genre.Genre
import com.tmdb.data.api.model.genre.GenresList
import com.tmdb.data.api.model.util.ApiResponse
import com.tmdb.data.api.model.util.NetworkErrorModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class GenreApiImpl(
    private val client: HttpClient,
    private val urlProvider: GenreUrlProvider
) : GenreApi {
    override suspend fun genreMovieList(
        language: String?,
    ): ApiResponse<List<Genre>, NetworkErrorModel> = runApiCall {
        client.get(urlProvider.genreMovieListUrl) {
            parameter("language", language)
        }.body<GenresList>().genres
    }

    override suspend fun genreTvList(
        language: String?,
    ): ApiResponse<List<Genre>, NetworkErrorModel> = runApiCall {
        client.get(urlProvider.genreTvListUrl) {
            parameter("language", language)
        }.body<GenresList>().genres
    }
}
