package com.tmdb.data.source.remote.movie

import com.tmdb.data.api.impl.movie.MockMovieApi
import com.tmdb.data.api.impl.movie.MovieApi
import com.tmdb.data.api.model.data.DataPage
import com.tmdb.data.api.model.util.ApiResponse
import com.tmdb.data.api.model.util.NetworkErrorModel
import com.tmdb.data.source.remote.contract.movie.MovieRemoteDataSource
import com.tmdb.data.source.remote.impl.movie.MovieRemoteDataSourceImpl
import com.tmdb.data.source.remote.util.model.ModelUtil
import kotlin.test.Test
import kotlin.test.assertSame
import kotlin.test.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.kodein.mock.Mocker
import org.kodein.mock.UsesMocks

@OptIn(ExperimentalCoroutinesApi::class)
@UsesMocks(MovieApi::class)
class MovieRemoteDataSourceMockLibraryTest {

    private val movieId = 550

    @Test
    fun `movie success`() = runTest {
        val mocker = Mocker()
        val mockedApi: MovieApi = MockMovieApi(mocker)
        val response = ApiResponse.Success(ModelUtil.movieModel)
        mocker.everySuspending { mockedApi.movie(movieId) } returns response

        val movieSource: MovieRemoteDataSource = MovieRemoteDataSourceImpl(mockedApi)
        movieSource.movie(movieId).run { assertSame(this, response) }
        mocker.verifyWithSuspend { called { mockedApi.movie(movieId) } }
    }

    @Test
    fun `movie network error`() = runTest {
        val mocker = Mocker()
        val mockedApi: MovieApi = MockMovieApi(mocker)
        val response = ApiResponse.NetworkError()
        mocker.everySuspending { mockedApi.movie(movieId) } returns response

        val movieSource: MovieRemoteDataSource = MovieRemoteDataSourceImpl(mockedApi)
        movieSource.movie(movieId).run { assertTrue(this.isNetworkError) }
        mocker.verifyWithSuspend { called { mockedApi.movie(movieId) } }
    }

    @Test
    fun `movie api error`() = runTest {
        val mocker = Mocker()
        val mockedApi: MovieApi = MockMovieApi(mocker)
        val response = ApiResponse.ApiError<NetworkErrorModel>()
        mocker.everySuspending { mockedApi.movie(movieId) } returns response

        val movieSource: MovieRemoteDataSource = MovieRemoteDataSourceImpl(mockedApi)
        movieSource.movie(movieId).run { assertTrue(this.isApiError) }
        mocker.verifyWithSuspend { called { mockedApi.movie(movieId) } }
    }

    @Test
    fun `movie unknown error`() = runTest {
        val mocker = Mocker()
        val mockedApi: MovieApi = MockMovieApi(mocker)
        val response = ApiResponse.UnknownError()
        mocker.everySuspending { mockedApi.movie(movieId) } returns response

        val movieSource: MovieRemoteDataSource = MovieRemoteDataSourceImpl(mockedApi)
        movieSource.movie(movieId).run { assertTrue(this.isUnknownError) }
        mocker.verifyWithSuspend { called { mockedApi.movie(movieId) } }
    }

    @Test
    fun `latest movie success`() = runTest {
        val mocker = Mocker()
        val mockedApi: MovieApi = MockMovieApi(mocker)
        val response = ApiResponse.Success(ModelUtil.movieModel)
        mocker.everySuspending { mockedApi.latestMovie() } returns response

        val movieSource: MovieRemoteDataSource = MovieRemoteDataSourceImpl(mockedApi)
        movieSource.latestMovie().run { assertSame(this, response) }
        mocker.verifyWithSuspend { called { mockedApi.latestMovie() } }
    }

    @Test
    fun `latest movie network error`() = runTest {
        val mocker = Mocker()
        val mockedApi: MovieApi = MockMovieApi(mocker)
        val response = ApiResponse.NetworkError()
        mocker.everySuspending { mockedApi.latestMovie() } returns response

        val movieSource: MovieRemoteDataSource = MovieRemoteDataSourceImpl(mockedApi)
        movieSource.latestMovie().run { assertTrue(this.isNetworkError) }
        mocker.verifyWithSuspend { called { mockedApi.latestMovie() } }
    }

    @Test
    fun `latest movie api error`() = runTest {
        val mocker = Mocker()
        val mockedApi: MovieApi = MockMovieApi(mocker)
        val response = ApiResponse.ApiError<NetworkErrorModel>()
        mocker.everySuspending { mockedApi.latestMovie() } returns response

        val movieSource: MovieRemoteDataSource = MovieRemoteDataSourceImpl(mockedApi)
        movieSource.latestMovie().run { assertTrue(this.isApiError) }
        mocker.verifyWithSuspend { called { mockedApi.latestMovie() } }
    }

    @Test
    fun `now playing movies success`() = runTest {
        val mocker = Mocker()
        val mockedApi: MovieApi = MockMovieApi(mocker)
        val response = ApiResponse.Success(
            DataPage(
                page = 1,
                results = listOf(ModelUtil.movieModel),
                totalPages = 1,
                totalResults = 1
            )
        )
        mocker.everySuspending { mockedApi.nowPlayingMovies() } returns response

        val movieSource: MovieRemoteDataSource = MovieRemoteDataSourceImpl(mockedApi)
        movieSource.nowPlayingMovies().run { assertSame(this, response) }
        mocker.verifyWithSuspend { called { mockedApi.nowPlayingMovies() } }
    }

    @Test
    fun `now playing movies network error`() = runTest {
        val mocker = Mocker()
        val mockedApi: MovieApi = MockMovieApi(mocker)
        val response = ApiResponse.NetworkError()
        mocker.everySuspending { mockedApi.nowPlayingMovies() } returns response

        val movieSource: MovieRemoteDataSource = MovieRemoteDataSourceImpl(mockedApi)
        movieSource.nowPlayingMovies().run { assertTrue(this.isNetworkError) }
        mocker.verifyWithSuspend { called { mockedApi.nowPlayingMovies() } }
    }

    @Test
    fun `now playing movies api error`() = runTest {
        val mocker = Mocker()
        val mockedApi: MovieApi = MockMovieApi(mocker)
        val response = ApiResponse.ApiError<NetworkErrorModel>()
        mocker.everySuspending { mockedApi.nowPlayingMovies() } returns response

        val movieSource: MovieRemoteDataSource = MovieRemoteDataSourceImpl(mockedApi)
        movieSource.nowPlayingMovies().run { assertTrue(this.isApiError) }
        mocker.verifyWithSuspend { called { mockedApi.nowPlayingMovies() } }
    }

    @Test
    fun `now playing movies unknown error`() = runTest {
        val mocker = Mocker()
        val mockedApi: MovieApi = MockMovieApi(mocker)
        val response = ApiResponse.UnknownError()
        mocker.everySuspending { mockedApi.nowPlayingMovies() } returns response

        val movieSource: MovieRemoteDataSource = MovieRemoteDataSourceImpl(mockedApi)
        movieSource.nowPlayingMovies().run { assertTrue(this.isUnknownError) }
        mocker.everySuspending { mockedApi.nowPlayingMovies() } returns response
    }

    @Test
    fun `now popular movies success`() = runTest {
        val mocker = Mocker()
        val mockedApi: MovieApi = MockMovieApi(mocker)
        val response = ApiResponse.Success(
            DataPage(
                page = 1,
                results = listOf(ModelUtil.movieModel),
                totalPages = 1,
                totalResults = 1
            )
        )
        mocker.everySuspending { mockedApi.nowPopularMovies() } returns response

        val movieSource: MovieRemoteDataSource = MovieRemoteDataSourceImpl(mockedApi)
        movieSource.nowPopularMovies().run { assertSame(this, response) }
        mocker.everySuspending { mockedApi.nowPopularMovies() } returns response
    }

    @Test
    fun `now popular movies network error`() = runTest {
        val mocker = Mocker()
        val mockedApi: MovieApi = MockMovieApi(mocker)
        val response = ApiResponse.NetworkError()
        mocker.everySuspending { mockedApi.nowPopularMovies() } returns response

        val movieSource: MovieRemoteDataSource = MovieRemoteDataSourceImpl(mockedApi)
        movieSource.nowPopularMovies().run { assertTrue(this.isNetworkError) }
        mocker.everySuspending { mockedApi.nowPopularMovies() } returns response
    }

    @Test
    fun `now popular movies api error`() = runTest {
        val mocker = Mocker()
        val mockedApi: MovieApi = MockMovieApi(mocker)
        val response = ApiResponse.ApiError<NetworkErrorModel>()
        mocker.everySuspending { mockedApi.nowPopularMovies() } returns response

        val movieSource: MovieRemoteDataSource = MovieRemoteDataSourceImpl(mockedApi)
        movieSource.nowPopularMovies().run { assertTrue(this.isApiError) }
        mocker.everySuspending { mockedApi.nowPopularMovies() } returns response
    }

    @Test
    fun `now popular movies unknown error`() = runTest {
        val mocker = Mocker()
        val mockedApi: MovieApi = MockMovieApi(mocker)
        val response = ApiResponse.UnknownError()
        mocker.everySuspending { mockedApi.nowPopularMovies() } returns response

        val movieSource: MovieRemoteDataSource = MovieRemoteDataSourceImpl(mockedApi)
        movieSource.nowPopularMovies().run { assertTrue(this.isUnknownError) }
        mocker.everySuspending { mockedApi.nowPopularMovies() } returns response
    }

    @Test
    fun `top rated movies success`() = runTest {
        val mocker = Mocker()
        val mockedApi: MovieApi = MockMovieApi(mocker)
        val response = ApiResponse.Success(
            DataPage(
                page = 1,
                results = listOf(ModelUtil.movieModel),
                totalPages = 1,
                totalResults = 1
            )
        )
        mocker.everySuspending { mockedApi.topRatedMovies() } returns response

        val movieSource: MovieRemoteDataSource = MovieRemoteDataSourceImpl(mockedApi)
        movieSource.topRatedMovies().run { assertSame(this, response) }
        mocker.everySuspending { mockedApi.topRatedMovies() } returns response
    }

    @Test
    fun `top rated movies network error`() = runTest {
        val mocker = Mocker()
        val mockedApi: MovieApi = MockMovieApi(mocker)
        val response = ApiResponse.NetworkError()
        mocker.everySuspending { mockedApi.topRatedMovies() } returns response

        val movieSource: MovieRemoteDataSource = MovieRemoteDataSourceImpl(mockedApi)
        movieSource.topRatedMovies().run { assertTrue(this.isNetworkError) }
        mocker.everySuspending { mockedApi.topRatedMovies() } returns response
    }

    @Test
    fun `top rated movies api error`() = runTest {
        val mocker = Mocker()
        val mockedApi: MovieApi = MockMovieApi(mocker)
        val response = ApiResponse.ApiError<NetworkErrorModel>()
        mocker.everySuspending { mockedApi.topRatedMovies() } returns response

        val movieSource: MovieRemoteDataSource = MovieRemoteDataSourceImpl(mockedApi)
        movieSource.topRatedMovies().run { assertTrue(this.isApiError) }
        mocker.everySuspending { mockedApi.topRatedMovies() } returns response
    }

    @Test
    fun `top rated movies unknown error`() = runTest {
        val mocker = Mocker()
        val mockedApi: MovieApi = MockMovieApi(mocker)
        val response = ApiResponse.UnknownError()
        mocker.everySuspending { mockedApi.topRatedMovies() } returns response

        val movieSource: MovieRemoteDataSource = MovieRemoteDataSourceImpl(mockedApi)
        movieSource.topRatedMovies().run { assertTrue(this.isUnknownError) }
        mocker.everySuspending { mockedApi.topRatedMovies() } returns response
    }

    @Test
    fun `upcoming movies success`() = runTest {
        val mocker = Mocker()
        val mockedApi: MovieApi = MockMovieApi(mocker)
        val response = ApiResponse.Success(
            DataPage(
                page = 1,
                results = listOf(ModelUtil.movieModel),
                totalPages = 1,
                totalResults = 1
            )
        )
        mocker.everySuspending { mockedApi.upcomingMovies() } returns response

        val movieSource: MovieRemoteDataSource = MovieRemoteDataSourceImpl(mockedApi)
        movieSource.upcomingMovies().run { assertSame(this, response) }
        mocker.everySuspending { mockedApi.upcomingMovies() } returns response
    }

    @Test
    fun `upcoming movies network error`() = runTest {
        val mocker = Mocker()
        val mockedApi: MovieApi = MockMovieApi(mocker)
        val response = ApiResponse.NetworkError()
        mocker.everySuspending { mockedApi.upcomingMovies() } returns response

        val movieSource: MovieRemoteDataSource = MovieRemoteDataSourceImpl(mockedApi)
        movieSource.upcomingMovies().run { assertTrue(this.isNetworkError) }
        mocker.everySuspending { mockedApi.upcomingMovies() } returns response
    }

    @Test
    fun `upcoming movies api error`() = runTest {
        val mocker = Mocker()
        val mockedApi: MovieApi = MockMovieApi(mocker)
        val response = ApiResponse.ApiError<NetworkErrorModel>()
        mocker.everySuspending { mockedApi.upcomingMovies() } returns response

        val movieSource: MovieRemoteDataSource = MovieRemoteDataSourceImpl(mockedApi)
        movieSource.upcomingMovies().run { assertTrue(this.isApiError) }
        mocker.everySuspending { mockedApi.upcomingMovies() } returns response
    }

    @Test
    fun `upcoming movies unknown error`() = runTest {
        val mocker = Mocker()
        val mockedApi: MovieApi = MockMovieApi(mocker)
        val response = ApiResponse.UnknownError()
        mocker.everySuspending { mockedApi.upcomingMovies() } returns response

        val movieSource: MovieRemoteDataSource = MovieRemoteDataSourceImpl(mockedApi)
        movieSource.upcomingMovies().run { assertTrue(this.isUnknownError) }

        mocker.everySuspending { mockedApi.upcomingMovies() } returns response
    }
}