package com.tmdb.data.api.impl.discover

import com.tmdb.data.api.config.url.provider.discover.DiscoverUrlProvider
import com.tmdb.data.api.model.data.DataPage
import com.tmdb.data.api.model.movie.Movie
import com.tmdb.data.api.model.util.ApiResponse
import com.tmdb.data.api.model.util.NetworkErrorModel
import com.tmdb.data.api.impl.util.runApiCall
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class DiscoverApiImpl(
    private val client: HttpClient,
    private val urlProvider: DiscoverUrlProvider
) : DiscoverApi {
    override suspend fun discoverMovie(
        language: String?,
        page: Int?,
        region: String?,
    ): ApiResponse<DataPage<Movie>, NetworkErrorModel> = runApiCall {
        client.get(urlProvider.discoverMovieUrl) {
            parameter("language", language)
            parameter("page", page)
            parameter("region", region)
        }.body()
    }

    override suspend fun discoverTv(
        language: String?,
        page: Int?,
        region: String?,
    ): ApiResponse<DataPage<Movie>, NetworkErrorModel> = runApiCall {
        client.get(urlProvider.discoverTvUrl) {
            parameter("language", language)
            parameter("page", page)
            parameter("region", region)
        }.body()
    }
}