package com.tmdb.data.source.remote.genre

import com.tmdb.data.api.impl.genre.GenreApi
import com.tmdb.data.api.model.genre.Genre
import com.tmdb.data.api.model.util.ApiResponse
import com.tmdb.data.api.model.util.NetworkErrorModel
import com.tmdb.data.source.remote.contract.genre.GenreRemoteDataSource
import com.tmdb.data.source.remote.impl.genre.GenreRemoteDataSourceImpl
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertSame
import kotlin.test.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest


@OptIn(ExperimentalCoroutinesApi::class)
class GenreRemoteDataSourceTest {
    private open class GenreApiMockAdapter : GenreApi {
        override suspend fun genreMovieList(
            language: String?
        ): ApiResponse<List<Genre>, NetworkErrorModel> {
            TODO("Not yet implemented")
        }

        override suspend fun genreTvList(
            language: String?
        ): ApiResponse<List<Genre>, NetworkErrorModel> {
            TODO("Not yet implemented")
        }
    }

    private val genreList = listOf(Genre(id = 28, name = "Action"))
    private val response = ApiResponse.Success(genreList)

    @Test
    fun `genre tv list success`() = runTest {
        var methodCallingCounter = 0
        val mockedApi = object: GenreApiMockAdapter() {
            override suspend fun genreTvList(
                language: String?
            ): ApiResponse<List<Genre>, NetworkErrorModel> {
                methodCallingCounter++
                return response
            }
        }
        val genreSource: GenreRemoteDataSource = GenreRemoteDataSourceImpl(mockedApi)
        genreSource.genreTvList().run { assertSame(this, response) }
        assertEquals(
            expected = methodCallingCounter,
            actual = 1,
            message = "DiscoverApi::genreTvList need to call 1 time"
        )
    }

    @Test
    fun `genre tv list network error`() = runTest {
        var methodCallingCounter = 0
        val mockedApi = object: GenreApiMockAdapter() {
            override suspend fun genreTvList(
                language: String?
            ): ApiResponse<List<Genre>, NetworkErrorModel> {
                methodCallingCounter++
                return ApiResponse.NetworkError()
            }
        }
        val genreSource: GenreRemoteDataSource = GenreRemoteDataSourceImpl(mockedApi)
        genreSource.genreTvList().run { assertTrue(this.isNetworkError) }
        assertEquals(
            expected = methodCallingCounter,
            actual = 1,
            message = "DiscoverApi::genreTvList need to call 1 time"
        )
    }

    @Test
    fun `genre tv list api error`() = runTest {
        var methodCallingCounter = 0
        val mockedApi = object: GenreApiMockAdapter() {
            override suspend fun genreTvList(
                language: String?
            ): ApiResponse<List<Genre>, NetworkErrorModel> {
                methodCallingCounter++
                return ApiResponse.ApiError()
            }
        }
        val genreSource: GenreRemoteDataSource = GenreRemoteDataSourceImpl(mockedApi)
        genreSource.genreTvList().run { assertTrue(this.isApiError) }
        assertEquals(
            expected = methodCallingCounter,
            actual = 1,
            message = "DiscoverApi::genreTvList need to call 1 time"
        )
    }

    @Test
    fun `genre tv list unknown error`() = runTest {
        var methodCallingCounter = 0
        val mockedApi = object: GenreApiMockAdapter() {
            override suspend fun genreTvList(
                language: String?
            ): ApiResponse<List<Genre>, NetworkErrorModel> {
                methodCallingCounter++
                return ApiResponse.UnknownError()
            }
        }
        val genreSource: GenreRemoteDataSource = GenreRemoteDataSourceImpl(mockedApi)
        genreSource.genreTvList().run { assertTrue(this.isUnknownError) }
        assertEquals(
            expected = methodCallingCounter,
            actual = 1,
            message = "DiscoverApi::genreTvList need to call 1 time"
        )
    }

    @Test
    fun `genre movie list success`() = runTest {
        var methodCallingCounter = 0
        val mockedApi = object: GenreApiMockAdapter() {
            override suspend fun genreMovieList(
                language: String?
            ): ApiResponse<List<Genre>, NetworkErrorModel> {
                methodCallingCounter++
                return response
            }
        }
        val genreSource: GenreRemoteDataSource = GenreRemoteDataSourceImpl(mockedApi)
        genreSource.genreMovieList().run { assertSame(this, response) }
        assertEquals(
            expected = methodCallingCounter,
            actual = 1,
            message = "DiscoverApi::genreMovieList need to call 1 time"
        )
    }

    @Test
    fun `genre movie list network error`() = runTest {
        var methodCallingCounter = 0
        val mockedApi = object: GenreApiMockAdapter() {
            override suspend fun genreMovieList(
                language: String?
            ): ApiResponse<List<Genre>, NetworkErrorModel> {
                methodCallingCounter++
                return ApiResponse.NetworkError()
            }
        }
        val genreSource: GenreRemoteDataSource = GenreRemoteDataSourceImpl(mockedApi)
        genreSource.genreMovieList().run { assertTrue(this.isNetworkError) }
        assertEquals(
            expected = methodCallingCounter,
            actual = 1,
            message = "DiscoverApi::genreMovieList need to call 1 time"
        )
    }

    @Test
    fun `genre movie list api error`() = runTest {
        var methodCallingCounter = 0
        val mockedApi = object: GenreApiMockAdapter() {
            override suspend fun genreMovieList(
                language: String?
            ): ApiResponse<List<Genre>, NetworkErrorModel> {
                methodCallingCounter++
                return ApiResponse.ApiError()
            }
        }
        val genreSource: GenreRemoteDataSource = GenreRemoteDataSourceImpl(mockedApi)
        genreSource.genreMovieList().run { assertTrue(this.isApiError) }
        assertEquals(
            expected = methodCallingCounter,
            actual = 1,
            message = "DiscoverApi::genreMovieList need to call 1 time"
        )
    }

    @Test
    fun `genre movie list unknown error`() = runTest {
        var methodCallingCounter = 0
        val mockedApi = object: GenreApiMockAdapter() {
            override suspend fun genreMovieList(
                language: String?
            ): ApiResponse<List<Genre>, NetworkErrorModel> {
                methodCallingCounter++
                return ApiResponse.UnknownError()
            }
        }
        val genreSource: GenreRemoteDataSource = GenreRemoteDataSourceImpl(mockedApi)
        genreSource.genreMovieList().run { assertTrue(this.isUnknownError) }
        assertEquals(
            expected = methodCallingCounter,
            actual = 1,
            message = "DiscoverApi::genreMovieList need to call 1 time"
        )
    }
}