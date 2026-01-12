package com.tmdb.data.source.remote.genre

import com.tmdb.data.api.impl.genre.GenreApi
import com.tmdb.data.api.model.genre.Genre
import com.tmdb.data.api.model.util.ApiResponse
import com.tmdb.data.api.model.util.NetworkErrorModel
import com.tmdb.data.source.remote.contract.genre.GenreRemoteDataSource
import com.tmdb.data.source.remote.impl.genre.GenreRemoteDataSourceImpl
import kotlin.test.Test
import kotlin.test.assertSame
import kotlin.test.assertTrue
import kotlinx.coroutines.test.runTest
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class GenreRemoteDataSourceMockitoTest {

    private val mockedApi: GenreApi = mock()
    private val genreSource: GenreRemoteDataSource = GenreRemoteDataSourceImpl(mockedApi)
    private val genreList = listOf(Genre(id = 28, name = "Action"))
    private val successResponse = ApiResponse.Success(genreList)

    @Test
    fun `genre tv list success`() = runTest {
        whenever(mockedApi.genreTvList()).thenReturn(successResponse)

        genreSource.genreTvList().run { assertSame(this, successResponse) }
        verify(mockedApi, times(1)).genreTvList()
    }

    @Test
    fun `genre tv list network error`() = runTest {
        val response = ApiResponse.NetworkError()
        whenever(mockedApi.genreTvList()).thenReturn(response)

        genreSource.genreTvList().run { assertTrue(this.isNetworkError) }
        verify(mockedApi, times(1)).genreTvList()
    }

    @Test
    fun `genre tv list api error`() = runTest {
        val response = ApiResponse.ApiError<NetworkErrorModel>()
        whenever(mockedApi.genreTvList()).thenReturn(response)

        genreSource.genreTvList().run { assertTrue(this.isApiError) }
        verify(mockedApi, times(1)).genreTvList()
    }

    @Test
    fun `genre tv list unknown error`() = runTest {
        val response = ApiResponse.UnknownError()
        whenever(mockedApi.genreTvList()).thenReturn(response)

        genreSource.genreTvList().run { assertTrue(this.isUnknownError) }
        verify(mockedApi, times(1)).genreTvList()
    }

    @Test
    fun `genre movie list success`() = runTest {
        whenever(mockedApi.genreMovieList()).thenReturn(successResponse)

        genreSource.genreMovieList().run { assertSame(this, successResponse) }
        verify(mockedApi, times(1)).genreMovieList()
    }

    @Test
    fun `genre movie list network error`() = runTest {
        val response = ApiResponse.NetworkError()
        whenever(mockedApi.genreMovieList()).thenReturn(response)

        genreSource.genreMovieList().run { assertTrue(this.isNetworkError) }
        verify(mockedApi, times(1)).genreMovieList()
    }

    @Test
    fun `genre movie list api error`() = runTest {
        val response = ApiResponse.ApiError<NetworkErrorModel>()
        whenever(mockedApi.genreMovieList()).thenReturn(response)

        genreSource.genreMovieList().run { assertTrue(this.isApiError) }
        verify(mockedApi, times(1)).genreMovieList()
    }

    @Test
    fun `genre movie list unknown error`() = runTest {
        val response = ApiResponse.UnknownError()
        whenever(mockedApi.genreMovieList()).thenReturn(response)

        genreSource.genreMovieList().run { assertTrue(this.isUnknownError) }
        verify(mockedApi, times(1)).genreMovieList()
    }
}
