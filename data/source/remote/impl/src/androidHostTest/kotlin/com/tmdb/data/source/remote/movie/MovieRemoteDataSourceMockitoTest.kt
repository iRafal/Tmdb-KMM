package com.tmdb.data.source.remote.movie

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
import kotlinx.coroutines.test.runTest
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class MovieRemoteDataSourceMockitoTest {

    private val mockedApi: MovieApi = mock()
    private val movieSource: MovieRemoteDataSource = MovieRemoteDataSourceImpl(mockedApi)
    private val movieId = 550

    @Test
    fun `movie success`() = runTest {
        val response = ApiResponse.Success(ModelUtil.movieModel)
        whenever(mockedApi.movie(movieId)).thenReturn(response)

        movieSource.movie(movieId).run { assertSame(this, response) }
        verify(mockedApi, times(1)).movie(movieId)
    }

    @Test
    fun `movie network error`() = runTest {
        val response = ApiResponse.NetworkError()
        whenever(mockedApi.movie(movieId)).thenReturn(response)

        movieSource.movie(movieId).run { assertTrue(this.isNetworkError) }
        verify(mockedApi, times(1)).movie(movieId)
    }

    @Test
    fun `movie api error`() = runTest {
        val response = ApiResponse.ApiError<NetworkErrorModel>()
        whenever(mockedApi.movie(movieId)).thenReturn(response)

        movieSource.movie(movieId).run { assertTrue(this.isApiError) }
        verify(mockedApi, times(1)).movie(movieId)
    }

    @Test
    fun `movie unknown error`() = runTest {
        val response = ApiResponse.UnknownError()
        whenever(mockedApi.movie(movieId)).thenReturn(response)

        movieSource.movie(movieId).run { assertTrue(this.isUnknownError) }
        verify(mockedApi, times(1)).movie(movieId)
    }

    @Test
    fun `latest movie success`() = runTest {
        val response = ApiResponse.Success(ModelUtil.movieModel)
        whenever(mockedApi.latestMovie()).thenReturn(response)

        movieSource.latestMovie().run { assertSame(this, response) }
        verify(mockedApi, times(1)).latestMovie()
    }

    @Test
    fun `latest movie network error`() = runTest {
        val response = ApiResponse.NetworkError()
        whenever(mockedApi.latestMovie()).thenReturn(response)

        movieSource.latestMovie().run { assertTrue(this.isNetworkError) }
        verify(mockedApi, times(1)).latestMovie()
    }

    @Test
    fun `latest movie api error`() = runTest {
        val response = ApiResponse.ApiError<NetworkErrorModel>()
        whenever(mockedApi.latestMovie()).thenReturn(response)

        movieSource.latestMovie().run { assertTrue(this.isApiError) }
        verify(mockedApi, times(1)).latestMovie()
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
        whenever(mockedApi.nowPlayingMovies()).thenReturn(response)

        movieSource.nowPlayingMovies().run { assertSame(this, response) }
        verify(mockedApi, times(1)).nowPlayingMovies()
    }

    @Test
    fun `now playing movies network error`() = runTest {
        val response = ApiResponse.NetworkError()
        whenever(mockedApi.nowPlayingMovies()).thenReturn(response)

        movieSource.nowPlayingMovies().run { assertTrue(this.isNetworkError) }
        verify(mockedApi, times(1)).nowPlayingMovies()
    }

    @Test
    fun `now playing movies api error`() = runTest {
        val response = ApiResponse.ApiError<NetworkErrorModel>()
        whenever(mockedApi.nowPlayingMovies()).thenReturn(response)

        movieSource.nowPlayingMovies().run { assertTrue(this.isApiError) }
        verify(mockedApi, times(1)).nowPlayingMovies()
    }

    @Test
    fun `now playing movies unknown error`() = runTest {
        val response = ApiResponse.UnknownError()
        whenever(mockedApi.nowPlayingMovies()).thenReturn(response)

        movieSource.nowPlayingMovies().run { assertTrue(this.isUnknownError) }
        verify(mockedApi, times(1)).nowPlayingMovies()
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
        whenever(mockedApi.nowPopularMovies()).thenReturn(response)

        movieSource.nowPopularMovies().run { assertSame(this, response) }
        verify(mockedApi, times(1)).nowPopularMovies()
    }

    @Test
    fun `now popular movies network error`() = runTest {
        val response = ApiResponse.NetworkError()
        whenever(mockedApi.nowPopularMovies()).thenReturn(response)

        movieSource.nowPopularMovies().run { assertTrue(this.isNetworkError) }
        verify(mockedApi, times(1)).nowPopularMovies()
    }

    @Test
    fun `now popular movies api error`() = runTest {
        val response = ApiResponse.ApiError<NetworkErrorModel>()
        whenever(mockedApi.nowPopularMovies()).thenReturn(response)

        movieSource.nowPopularMovies().run { assertTrue(this.isApiError) }
        verify(mockedApi, times(1)).nowPopularMovies()
    }

    @Test
    fun `now popular movies unknown error`() = runTest {
        val response = ApiResponse.UnknownError()
        whenever(mockedApi.nowPopularMovies()).thenReturn(response)

        movieSource.nowPopularMovies().run { assertTrue(this.isUnknownError) }
        verify(mockedApi, times(1)).nowPopularMovies()
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
        whenever(mockedApi.topRatedMovies()).thenReturn(response)

        movieSource.topRatedMovies().run { assertSame(this, response) }
        verify(mockedApi, times(1)).topRatedMovies()
    }

    @Test
    fun `top rated movies network error`() = runTest {
        val response = ApiResponse.NetworkError()
        whenever(mockedApi.topRatedMovies()).thenReturn(response)

        movieSource.topRatedMovies().run { assertTrue(this.isNetworkError) }
        verify(mockedApi, times(1)).topRatedMovies()
    }

    @Test
    fun `top rated movies api error`() = runTest {
        val response = ApiResponse.ApiError<NetworkErrorModel>()
        whenever(mockedApi.topRatedMovies()).thenReturn(response)

        movieSource.topRatedMovies().run { assertTrue(this.isApiError) }
        verify(mockedApi, times(1)).topRatedMovies()
    }

    @Test
    fun `top rated movies unknown error`() = runTest {
        val response = ApiResponse.UnknownError()
        whenever(mockedApi.topRatedMovies()).thenReturn(response)

        movieSource.topRatedMovies().run { assertTrue(this.isUnknownError) }
        verify(mockedApi, times(1)).topRatedMovies()
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
        whenever(mockedApi.upcomingMovies()).thenReturn(response)

        movieSource.upcomingMovies().run { assertSame(this, response) }
        verify(mockedApi, times(1)).upcomingMovies()
    }

    @Test
    fun `upcoming movies network error`() = runTest {
        val response = ApiResponse.NetworkError()
        whenever(mockedApi.upcomingMovies()).thenReturn(response)

        movieSource.upcomingMovies().run { assertTrue(this.isNetworkError) }
        verify(mockedApi, times(1)).upcomingMovies()
    }

    @Test
    fun `upcoming movies api error`() = runTest {
        val response = ApiResponse.ApiError<NetworkErrorModel>()
        whenever(mockedApi.upcomingMovies()).thenReturn(response)

        movieSource.upcomingMovies().run { assertTrue(this.isApiError) }
        verify(mockedApi, times(1)).upcomingMovies()
    }

    @Test
    fun `upcoming movies unknown error`() = runTest {
        val response = ApiResponse.UnknownError()
        whenever(mockedApi.upcomingMovies()).thenReturn(response)

        movieSource.upcomingMovies().run { assertTrue(this.isUnknownError) }
        verify(mockedApi, times(1)).upcomingMovies()
    }
}
