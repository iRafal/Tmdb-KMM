package com.tmdb.data.source.remote.movie

import com.tmdb.data.api.impl.movie.MovieApi
import com.tmdb.data.api.model.data.DataPage
import com.tmdb.data.api.model.movie.Movie
import com.tmdb.data.api.model.util.ApiResponse
import com.tmdb.data.api.model.util.NetworkErrorModel
import com.tmdb.data.source.remote.contract.movie.MovieRemoteDataSource
import com.tmdb.data.source.remote.impl.movie.MovieRemoteDataSourceImpl
import com.tmdb.data.source.remote.util.model.ModelUtil
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertSame
import kotlin.test.assertTrue
import kotlinx.coroutines.test.runTest

class MovieRemoteDataSourceTest {
    private open class MovieApiMockAdapter : MovieApi {
        override suspend fun movie(
            movieId: Int,
            language: String?,
            appendToResponse: String?,
        ): ApiResponse<Movie, NetworkErrorModel> {
            TODO("Not yet implemented")
        }

        override suspend fun latestMovie(language: String?): ApiResponse<Movie, NetworkErrorModel> {
            TODO("Not yet implemented")
        }

        override suspend fun nowPlayingMovies(
            language: String?,
            page: Int?,
            region: String?,
        ): ApiResponse<DataPage<Movie>, NetworkErrorModel> {
            TODO("Not yet implemented")
        }

        override suspend fun nowPopularMovies(
            language: String?,
            page: Int?,
            region: String?,
        ): ApiResponse<DataPage<Movie>, NetworkErrorModel> {
            TODO("Not yet implemented")
        }

        override suspend fun topRatedMovies(
            language: String?,
            page: Int?,
            region: String?,
        ): ApiResponse<DataPage<Movie>, NetworkErrorModel> {
            TODO("Not yet implemented")
        }

        override suspend fun upcomingMovies(
            language: String?,
            page: Int?,
            region: String?,
        ): ApiResponse<DataPage<Movie>, NetworkErrorModel> {
            TODO("Not yet implemented")
        }
    }

    private val movieId = 550

    @Test
    fun `movie success`() = runTest {
        val response = ApiResponse.Success(ModelUtil.movieModel)
        var methodCallingCounter = 0
        val mockedApi = object : MovieApiMockAdapter() {
            override suspend fun movie(
                movieId: Int,
                language: String?,
                appendToResponse: String?,
            ): ApiResponse<Movie, NetworkErrorModel> {
                methodCallingCounter++
                return response
            }
        }
        val movieSource: MovieRemoteDataSource = MovieRemoteDataSourceImpl(mockedApi)
        movieSource.movie(movieId).run { assertSame(this, response) }
        assertEquals(
            expected = methodCallingCounter,
            actual = 1,
            message = "MovieApi::movie need to call 1 time",
        )
    }

    @Test
    fun `movie network error`() = runTest {
        var methodCallingCounter = 0
        val mockedApi = object : MovieApiMockAdapter() {
            override suspend fun movie(
                movieId: Int,
                language: String?,
                appendToResponse: String?,
            ): ApiResponse<Movie, NetworkErrorModel> {
                methodCallingCounter++
                return ApiResponse.NetworkError()
            }
        }
        val movieSource: MovieRemoteDataSource = MovieRemoteDataSourceImpl(mockedApi)
        movieSource.movie(movieId).run { assertTrue(this.isNetworkError) }
        assertEquals(
            expected = methodCallingCounter,
            actual = 1,
            message = "MovieApi::movie need to call 1 time",
        )
    }

    @Test
    fun `movie api error`() = runTest {
        var methodCallingCounter = 0
        val mockedApi = object : MovieApiMockAdapter() {
            override suspend fun movie(
                movieId: Int,
                language: String?,
                appendToResponse: String?,
            ): ApiResponse<Movie, NetworkErrorModel> {
                methodCallingCounter++
                return ApiResponse.ApiError()
            }
        }
        val movieSource: MovieRemoteDataSource = MovieRemoteDataSourceImpl(mockedApi)
        movieSource.movie(movieId).run { assertTrue(this.isApiError) }
        assertEquals(
            expected = methodCallingCounter,
            actual = 1,
            message = "MovieApi::movie need to call 1 time",
        )
    }

    @Test
    fun `movie unknown error`() = runTest {
        var methodCallingCounter = 0
        val mockedApi = object : MovieApiMockAdapter() {
            override suspend fun movie(
                movieId: Int,
                language: String?,
                appendToResponse: String?,
            ): ApiResponse<Movie, NetworkErrorModel> {
                methodCallingCounter++
                return ApiResponse.UnknownError()
            }
        }
        val movieSource: MovieRemoteDataSource = MovieRemoteDataSourceImpl(mockedApi)
        movieSource.movie(movieId).run { assertTrue(this.isUnknownError) }
        assertEquals(
            expected = methodCallingCounter,
            actual = 1,
            message = "MovieApi::movie need to call 1 time",
        )
    }

    @Test
    fun `latest movie success`() = runTest {
        val response = ApiResponse.Success(ModelUtil.movieModel)
        var methodCallingCounter = 0
        val mockedApi = object : MovieApiMockAdapter() {
            override suspend fun latestMovie(
                language: String?,
            ): ApiResponse<Movie, NetworkErrorModel> {
                methodCallingCounter++
                return response
            }
        }
        val movieSource: MovieRemoteDataSource = MovieRemoteDataSourceImpl(mockedApi)
        movieSource.latestMovie().run { assertSame(this, response) }
        assertEquals(
            expected = methodCallingCounter,
            actual = 1,
            message = "MovieApi::latestMovie need to call 1 time",
        )
    }

    @Test
    fun `latest movie network error`() = runTest {
        var methodCallingCounter = 0
        val mockedApi = object : MovieApiMockAdapter() {
            override suspend fun latestMovie(
                language: String?,
            ): ApiResponse<Movie, NetworkErrorModel> {
                methodCallingCounter++
                return ApiResponse.NetworkError()
            }
        }
        val movieSource: MovieRemoteDataSource = MovieRemoteDataSourceImpl(mockedApi)
        movieSource.latestMovie().run { assertTrue(this.isNetworkError) }
        assertEquals(
            expected = methodCallingCounter,
            actual = 1,
            message = "MovieApi::latestMovie need to call 1 time",
        )
    }

    @Test
    fun `latest movie api error`() = runTest {
        var methodCallingCounter = 0
        val mockedApi = object : MovieApiMockAdapter() {
            override suspend fun latestMovie(
                language: String?,
            ): ApiResponse<Movie, NetworkErrorModel> {
                methodCallingCounter++
                return ApiResponse.ApiError()
            }
        }
        val movieSource: MovieRemoteDataSource = MovieRemoteDataSourceImpl(mockedApi)
        movieSource.latestMovie().run { assertTrue(this.isApiError) }
        assertEquals(
            expected = methodCallingCounter,
            actual = 1,
            message = "MovieApi::latestMovie need to call 1 time",
        )
    }

    @Test
    fun `now playing movies success`() = runTest {
        val response = ApiResponse.Success(
            DataPage(
                page = 1,
                results = listOf(ModelUtil.movieModel),
                totalPages = 1,
                totalResults = 1,
            ),
        )
        var methodCallingCounter = 0
        val mockedApi = object : MovieApiMockAdapter() {
            override suspend fun nowPlayingMovies(
                language: String?,
                page: Int?,
                region: String?,
            ): ApiResponse<DataPage<Movie>, NetworkErrorModel> {
                methodCallingCounter++
                return response
            }
        }
        val movieSource: MovieRemoteDataSource = MovieRemoteDataSourceImpl(mockedApi)
        movieSource.nowPlayingMovies().run { assertSame(this, response) }
        assertEquals(
            expected = methodCallingCounter,
            actual = 1,
            message = "MovieApi::nowPlayingMovies need to call 1 time",
        )
    }

    @Test
    fun `now playing movies network error`() = runTest {
        var methodCallingCounter = 0
        val mockedApi = object : MovieApiMockAdapter() {
            override suspend fun nowPlayingMovies(
                language: String?,
                page: Int?,
                region: String?,
            ): ApiResponse<DataPage<Movie>, NetworkErrorModel> {
                methodCallingCounter++
                return ApiResponse.NetworkError()
            }
        }
        val movieSource: MovieRemoteDataSource = MovieRemoteDataSourceImpl(mockedApi)
        movieSource.nowPlayingMovies().run { assertTrue(this.isNetworkError) }
        assertEquals(
            expected = methodCallingCounter,
            actual = 1,
            message = "MovieApi::nowPlayingMovies need to call 1 time",
        )
    }

    @Test
    fun `now playing movies api error`() = runTest {
        var methodCallingCounter = 0
        val mockedApi = object : MovieApiMockAdapter() {
            override suspend fun nowPlayingMovies(
                language: String?,
                page: Int?,
                region: String?,
            ): ApiResponse<DataPage<Movie>, NetworkErrorModel> {
                methodCallingCounter++
                return ApiResponse.ApiError()
            }
        }
        val movieSource: MovieRemoteDataSource = MovieRemoteDataSourceImpl(mockedApi)
        movieSource.nowPlayingMovies().run { assertTrue(this.isApiError) }
        assertEquals(
            expected = methodCallingCounter,
            actual = 1,
            message = "MovieApi::nowPlayingMovies need to call 1 time",
        )
    }

    @Test
    fun `now playing movies unknown error`() = runTest {
        var methodCallingCounter = 0
        val mockedApi = object : MovieApiMockAdapter() {
            override suspend fun nowPlayingMovies(
                language: String?,
                page: Int?,
                region: String?,
            ): ApiResponse<DataPage<Movie>, NetworkErrorModel> {
                methodCallingCounter++
                return ApiResponse.UnknownError()
            }
        }
        val movieSource: MovieRemoteDataSource = MovieRemoteDataSourceImpl(mockedApi)
        movieSource.nowPlayingMovies().run { assertTrue(this.isUnknownError) }
        assertEquals(
            expected = methodCallingCounter,
            actual = 1,
            message = "MovieApi::nowPlayingMovies need to call 1 time",
        )
    }

    @Test
    fun `now popular movies success`() = runTest {
        val response = ApiResponse.Success(
            DataPage(
                page = 1,
                results = listOf(ModelUtil.movieModel),
                totalPages = 1,
                totalResults = 1,
            ),
        )
        var methodCallingCounter = 0
        val mockedApi = object : MovieApiMockAdapter() {
            override suspend fun nowPopularMovies(
                language: String?,
                page: Int?,
                region: String?,
            ): ApiResponse<DataPage<Movie>, NetworkErrorModel> {
                methodCallingCounter++
                return response
            }
        }
        val movieSource: MovieRemoteDataSource = MovieRemoteDataSourceImpl(mockedApi)
        movieSource.nowPopularMovies().run { assertSame(this, response) }
        assertEquals(
            expected = methodCallingCounter,
            actual = 1,
            message = "MovieApi::nowPopularMovies need to call 1 time",
        )
    }

    @Test
    fun `now popular movies network error`() = runTest {
        var methodCallingCounter = 0
        val mockedApi = object : MovieApiMockAdapter() {
            override suspend fun nowPopularMovies(
                language: String?,
                page: Int?,
                region: String?,
            ): ApiResponse<DataPage<Movie>, NetworkErrorModel> {
                methodCallingCounter++
                return ApiResponse.NetworkError()
            }
        }
        val movieSource: MovieRemoteDataSource = MovieRemoteDataSourceImpl(mockedApi)
        movieSource.nowPopularMovies().run { assertTrue(this.isNetworkError) }
        assertEquals(
            expected = methodCallingCounter,
            actual = 1,
            message = "MovieApi::nowPopularMovies need to call 1 time",
        )
    }

    @Test
    fun `now popular movies api error`() = runTest {
        var methodCallingCounter = 0
        val mockedApi = object : MovieApiMockAdapter() {
            override suspend fun nowPopularMovies(
                language: String?,
                page: Int?,
                region: String?,
            ): ApiResponse<DataPage<Movie>, NetworkErrorModel> {
                methodCallingCounter++
                return ApiResponse.ApiError()
            }
        }
        val movieSource: MovieRemoteDataSource = MovieRemoteDataSourceImpl(mockedApi)
        movieSource.nowPopularMovies().run { assertTrue(this.isApiError) }
        assertEquals(
            expected = methodCallingCounter,
            actual = 1,
            message = "MovieApi::nowPopularMovies need to call 1 time",
        )
    }

    @Test
    fun `now popular movies unknown error`() = runTest {
        var methodCallingCounter = 0
        val mockedApi = object : MovieApiMockAdapter() {
            override suspend fun nowPopularMovies(
                language: String?,
                page: Int?,
                region: String?,
            ): ApiResponse<DataPage<Movie>, NetworkErrorModel> {
                methodCallingCounter++
                return ApiResponse.UnknownError()
            }
        }
        val movieSource: MovieRemoteDataSource = MovieRemoteDataSourceImpl(mockedApi)
        movieSource.nowPopularMovies().run { assertTrue(this.isUnknownError) }
        assertEquals(
            expected = methodCallingCounter,
            actual = 1,
            message = "MovieApi::nowPopularMovies need to call 1 time",
        )
    }

    @Test
    fun `top rated movies success`() = runTest {
        val response = ApiResponse.Success(
            DataPage(
                page = 1,
                results = listOf(ModelUtil.movieModel),
                totalPages = 1,
                totalResults = 1,
            ),
        )
        var methodCallingCounter = 0
        val mockedApi = object : MovieApiMockAdapter() {
            override suspend fun topRatedMovies(
                language: String?,
                page: Int?,
                region: String?,
            ): ApiResponse<DataPage<Movie>, NetworkErrorModel> {
                methodCallingCounter++
                return response
            }
        }
        val movieSource: MovieRemoteDataSource = MovieRemoteDataSourceImpl(mockedApi)
        movieSource.topRatedMovies().run { assertSame(this, response) }
        assertEquals(
            expected = methodCallingCounter,
            actual = 1,
            message = "MovieApi::topRatedMovies need to call 1 time",
        )
    }

    @Test
    fun `top rated movies network error`() = runTest {
        var methodCallingCounter = 0
        val mockedApi = object : MovieApiMockAdapter() {
            override suspend fun topRatedMovies(
                language: String?,
                page: Int?,
                region: String?,
            ): ApiResponse<DataPage<Movie>, NetworkErrorModel> {
                methodCallingCounter++
                return ApiResponse.NetworkError()
            }
        }
        val movieSource: MovieRemoteDataSource = MovieRemoteDataSourceImpl(mockedApi)
        movieSource.topRatedMovies().run { assertTrue(this.isNetworkError) }
        assertEquals(
            expected = methodCallingCounter,
            actual = 1,
            message = "MovieApi::topRatedMovies need to call 1 time",
        )
    }

    @Test
    fun `top rated movies api error`() = runTest {
        var methodCallingCounter = 0
        val mockedApi = object : MovieApiMockAdapter() {
            override suspend fun topRatedMovies(
                language: String?,
                page: Int?,
                region: String?,
            ): ApiResponse<DataPage<Movie>, NetworkErrorModel> {
                methodCallingCounter++
                return ApiResponse.ApiError()
            }
        }
        val movieSource: MovieRemoteDataSource = MovieRemoteDataSourceImpl(mockedApi)
        movieSource.topRatedMovies().run { assertTrue(this.isApiError) }
        assertEquals(
            expected = methodCallingCounter,
            actual = 1,
            message = "MovieApi::topRatedMovies need to call 1 time",
        )
    }

    @Test
    fun `top rated movies unknown error`() = runTest {
        var methodCallingCounter = 0
        val mockedApi = object : MovieApiMockAdapter() {
            override suspend fun topRatedMovies(
                language: String?,
                page: Int?,
                region: String?,
            ): ApiResponse<DataPage<Movie>, NetworkErrorModel> {
                methodCallingCounter++
                return ApiResponse.UnknownError()
            }
        }
        val movieSource: MovieRemoteDataSource = MovieRemoteDataSourceImpl(mockedApi)
        movieSource.topRatedMovies().run { assertTrue(this.isUnknownError) }
        assertEquals(
            expected = methodCallingCounter,
            actual = 1,
            message = "MovieApi::topRatedMovies need to call 1 time",
        )
    }

    @Test
    fun `upcoming movies success`() = runTest {
        val response = ApiResponse.Success(
            DataPage(
                page = 1,
                results = listOf(ModelUtil.movieModel),
                totalPages = 1,
                totalResults = 1,
            ),
        )
        var methodCallingCounter = 0
        val mockedApi = object : MovieApiMockAdapter() {
            override suspend fun upcomingMovies(
                language: String?,
                page: Int?,
                region: String?,
            ): ApiResponse<DataPage<Movie>, NetworkErrorModel> {
                methodCallingCounter++
                return response
            }
        }
        val movieSource: MovieRemoteDataSource = MovieRemoteDataSourceImpl(mockedApi)
        movieSource.upcomingMovies().run { assertSame(this, response) }
        assertEquals(
            expected = methodCallingCounter,
            actual = 1,
            message = "MovieApi::upcomingMovies need to call 1 time",
        )
    }

    @Test
    fun `upcoming movies network error`() = runTest {
        var methodCallingCounter = 0
        val mockedApi = object : MovieApiMockAdapter() {
            override suspend fun upcomingMovies(
                language: String?,
                page: Int?,
                region: String?,
            ): ApiResponse<DataPage<Movie>, NetworkErrorModel> {
                methodCallingCounter++
                return ApiResponse.NetworkError()
            }
        }
        val movieSource: MovieRemoteDataSource = MovieRemoteDataSourceImpl(mockedApi)
        movieSource.upcomingMovies().run { assertTrue(this.isNetworkError) }
        assertEquals(
            expected = methodCallingCounter,
            actual = 1,
            message = "MovieApi::upcomingMovies need to call 1 time",
        )
    }

    @Test
    fun `upcoming movies api error`() = runTest {
        var methodCallingCounter = 0
        val mockedApi = object : MovieApiMockAdapter() {
            override suspend fun upcomingMovies(
                language: String?,
                page: Int?,
                region: String?,
            ): ApiResponse<DataPage<Movie>, NetworkErrorModel> {
                methodCallingCounter++
                return ApiResponse.ApiError()
            }
        }
        val movieSource: MovieRemoteDataSource = MovieRemoteDataSourceImpl(mockedApi)
        movieSource.upcomingMovies().run { assertTrue(this.isApiError) }
        assertEquals(
            expected = methodCallingCounter,
            actual = 1,
            message = "MovieApi::upcomingMovies need to call 1 time",
        )
    }

    @Test
    fun `upcoming movies unknown error`() = runTest {
        var methodCallingCounter = 0
        val mockedApi = object : MovieApiMockAdapter() {
            override suspend fun upcomingMovies(
                language: String?,
                page: Int?,
                region: String?,
            ): ApiResponse<DataPage<Movie>, NetworkErrorModel> {
                methodCallingCounter++
                return ApiResponse.UnknownError()
            }
        }
        val movieSource: MovieRemoteDataSource = MovieRemoteDataSourceImpl(mockedApi)
        movieSource.upcomingMovies().run { assertTrue(this.isUnknownError) }
        assertEquals(
            expected = methodCallingCounter,
            actual = 1,
            message = "MovieApi::upcomingMovies need to call 1 time",
        )
    }
}
