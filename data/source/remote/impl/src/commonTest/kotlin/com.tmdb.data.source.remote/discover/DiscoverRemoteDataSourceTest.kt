package com.tmdb.data.source.remote.discover

import com.tmdb.data.api.impl.discover.DiscoverApi
import com.tmdb.data.api.model.data.DataPage
import com.tmdb.data.api.model.movie.Movie
import com.tmdb.data.api.model.util.ApiResponse
import com.tmdb.data.api.model.util.NetworkErrorModel
import com.tmdb.data.source.remote.contract.discover.DiscoverRemoteDataSource
import com.tmdb.data.source.remote.impl.discover.DiscoverRemoteDataSourceImpl
import com.tmdb.data.source.remote.util.model.ModelUtil
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertSame
import kotlin.test.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest

private open class DiscoverApiMockAdapter : DiscoverApi {
    override suspend fun discoverMovie(
        language: String?,
        page: Int?,
        region: String?,
    ): ApiResponse<DataPage<Movie>, NetworkErrorModel> {
        TODO("Not yet implemented")
    }

    override suspend fun discoverTv(
        language: String?,
        page: Int?,
        region: String?,
    ): ApiResponse<DataPage<Movie>, NetworkErrorModel> {
        TODO("Not yet implemented")
    }
}

@OptIn(ExperimentalCoroutinesApi::class)
class DiscoverRemoteDataSourceTest {
    @Test
    fun `discover movie list success`() = runTest {
        val response = ApiResponse.Success(
            DataPage(
                page = 1,
                results = listOf(ModelUtil.movieModel),
                totalPages = 1,
                totalResults = 1,
            ),
        )
        var methodCallingCounter = 0
        val mockedApi = object : DiscoverApiMockAdapter() {
            override suspend fun discoverMovie(
                language: String?,
                page: Int?,
                region: String?,
            ): ApiResponse<DataPage<Movie>, NetworkErrorModel> {
                methodCallingCounter++
                return response
            }
        }
        val discoverSource: DiscoverRemoteDataSource = DiscoverRemoteDataSourceImpl(mockedApi)
        discoverSource.discoverMovie().run { assertSame(response, this) }
        assertEquals(
            expected = methodCallingCounter,
            actual = 1,
            message = "DiscoverApi::discoverMovie need to call 1 time",
        )
    }

    @Test
    fun `discover movie list network error`() = runTest {
        var methodCallingCounter = 0
        val mockedApi = object : DiscoverApiMockAdapter() {
            override suspend fun discoverMovie(
                language: String?,
                page: Int?,
                region: String?,
            ): ApiResponse<DataPage<Movie>, NetworkErrorModel> {
                methodCallingCounter++
                return ApiResponse.NetworkError()
            }
        }
        val discoverSource: DiscoverRemoteDataSource = DiscoverRemoteDataSourceImpl(mockedApi)
        discoverSource.discoverMovie().run { assertTrue(this.isNetworkError) }
        assertEquals(
            expected = methodCallingCounter,
            actual = 1,
            message = "DiscoverApi::discoverMovie need to call 1 time",
        )
    }

    @Test
    fun `discover movie list api error`() = runTest {
        var methodCallingCounter = 0
        val mockedApi = object : DiscoverApiMockAdapter() {
            override suspend fun discoverMovie(
                language: String?,
                page: Int?,
                region: String?,
            ): ApiResponse<DataPage<Movie>, NetworkErrorModel> {
                methodCallingCounter++
                return ApiResponse.ApiError()
            }
        }
        val discoverSource: DiscoverRemoteDataSource = DiscoverRemoteDataSourceImpl(mockedApi)
        discoverSource.discoverMovie().run { assertTrue(this.isApiError) }
        assertEquals(
            expected = methodCallingCounter,
            actual = 1,
            message = "DiscoverApi::discoverMovie need to call 1 time",
        )
    }

    @Test
    fun `discover movie list unknown error`() = runTest {
        var methodCallingCounter = 0
        val mockedApi = object : DiscoverApiMockAdapter() {
            override suspend fun discoverMovie(
                language: String?,
                page: Int?,
                region: String?,
            ): ApiResponse<DataPage<Movie>, NetworkErrorModel> {
                methodCallingCounter++
                return ApiResponse.UnknownError()
            }
        }
        val discoverSource: DiscoverRemoteDataSource = DiscoverRemoteDataSourceImpl(mockedApi)
        discoverSource.discoverMovie().run { assertTrue(this.isUnknownError) }
        assertEquals(
            expected = methodCallingCounter,
            actual = 1,
            message = "DiscoverApi::discoverMovie need to call 1 time",
        )
    }

    @Test
    fun `discover tv list success`() = runTest {
        val response = ApiResponse.Success(
            DataPage(
                page = 1,
                results = listOf(ModelUtil.movieModel),
                totalPages = 1,
                totalResults = 1,
            ),
        )
        var methodCallingCounter = 0
        val mockedApi = object : DiscoverApiMockAdapter() {
            override suspend fun discoverTv(
                language: String?,
                page: Int?,
                region: String?,
            ): ApiResponse<DataPage<Movie>, NetworkErrorModel> {
                methodCallingCounter++
                return response
            }
        }
        val discoverSource: DiscoverRemoteDataSource = DiscoverRemoteDataSourceImpl(mockedApi)
        discoverSource.discoverTv().run { assertSame(this, response) }
        assertEquals(
            expected = methodCallingCounter,
            actual = 1,
            message = "DiscoverApi::discoverTv need to call 1 time",
        )
    }

    @Test
    fun `discover tv list network error`() = runTest {
        var methodCallingCounter = 0
        val mockedApi = object : DiscoverApiMockAdapter() {
            override suspend fun discoverTv(
                language: String?,
                page: Int?,
                region: String?,
            ): ApiResponse<DataPage<Movie>, NetworkErrorModel> {
                methodCallingCounter++
                return ApiResponse.NetworkError()
            }
        }
        val discoverSource: DiscoverRemoteDataSource = DiscoverRemoteDataSourceImpl(mockedApi)
        discoverSource.discoverTv().run { assertTrue(this.isNetworkError) }
        assertEquals(
            expected = methodCallingCounter,
            actual = 1,
            message = "DiscoverApi::discoverTv need to call 1 time",
        )
    }

    @Test
    fun `discover tv list api error`() = runTest {
        var methodCallingCounter = 0
        val mockedApi = object : DiscoverApiMockAdapter() {
            override suspend fun discoverTv(
                language: String?,
                page: Int?,
                region: String?,
            ): ApiResponse<DataPage<Movie>, NetworkErrorModel> {
                methodCallingCounter++
                return ApiResponse.ApiError()
            }
        }
        val discoverSource: DiscoverRemoteDataSource = DiscoverRemoteDataSourceImpl(mockedApi)
        discoverSource.discoverTv().run { assertTrue(this.isApiError) }
        assertEquals(
            expected = methodCallingCounter,
            actual = 1,
            message = "DiscoverApi::discoverTv need to call 1 time",
        )
    }

    @Test
    fun `discover tv list unknown error`() = runTest {
        var methodCallingCounter = 0
        val mockedApi = object : DiscoverApiMockAdapter() {
            override suspend fun discoverTv(
                language: String?,
                page: Int?,
                region: String?,
            ): ApiResponse<DataPage<Movie>, NetworkErrorModel> {
                methodCallingCounter++
                return ApiResponse.UnknownError()
            }
        }
        val discoverSource: DiscoverRemoteDataSource = DiscoverRemoteDataSourceImpl(mockedApi)
        discoverSource.discoverTv().run { assertTrue(this.isUnknownError) }
        assertEquals(
            expected = methodCallingCounter,
            actual = 1,
            message = "DiscoverApi::discoverTv need to call 1 time",
        )
    }
}
