package com.tmdb.data.source.remote.impl.discover

import com.tmdb.data.api.impl.discover.DiscoverApi
import com.tmdb.data.api.model.data.DataPage
import com.tmdb.data.api.model.movie.Movie
import com.tmdb.data.api.model.util.ApiResponse
import com.tmdb.data.api.model.util.NetworkErrorModel
import com.tmdb.data.source.remote.contract.discover.DiscoverRemoteDataSource

class DiscoverRemoteDataSourceImpl(
    private val api: DiscoverApi,
) : DiscoverRemoteDataSource {

    override suspend fun discoverTv(
        language: String?,
        page: Int?,
        region: String?,
    ): ApiResponse<DataPage<Movie>, NetworkErrorModel> = api.discoverTv(language, page, region)

    override suspend fun discoverMovie(
        language: String?,
        page: Int?,
        region: String?,
    ): ApiResponse<DataPage<Movie>, NetworkErrorModel> = api.discoverMovie(language, page, region)
}
