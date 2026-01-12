package com.tmdb.data.source.remote.discover

import com.tmdb.data.api.impl.discover.DiscoverApi
import com.tmdb.data.api.model.data.DataPage
import com.tmdb.data.api.model.util.ApiResponse
import com.tmdb.data.api.model.util.ApiResponse.Success
import com.tmdb.data.api.model.util.NetworkErrorModel
import com.tmdb.data.source.remote.contract.discover.DiscoverRemoteDataSource
import com.tmdb.data.source.remote.impl.discover.DiscoverRemoteDataSourceImpl
import com.tmdb.data.source.remote.util.model.ModelUtil
import kotlin.test.Test
import kotlin.test.assertSame
import kotlin.test.assertTrue
import kotlinx.coroutines.test.runTest
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class DiscoverRemoteDataSourceMockitoTest {

    private val mockedApi: DiscoverApi = mock()
    private val discoverSource: DiscoverRemoteDataSource = DiscoverRemoteDataSourceImpl(mockedApi)

    @Test
    fun `discover movie list success`() = runTest {
        val response = Success(
            DataPage(
                page = 1,
                results = listOf(ModelUtil.movieModel),
                totalPages = 1,
                totalResults = 1,
            ),
        )
        whenever(mockedApi.discoverMovie()).thenReturn(response)

        discoverSource.discoverMovie().run { assertSame(response, this) }
        verify(mockedApi, times(1)).discoverMovie()
    }

    @Test
    fun `discover movie list network error`() = runTest {
        val response = ApiResponse.NetworkError()
        whenever(mockedApi.discoverMovie()).thenReturn(response)

        discoverSource.discoverMovie().run { assertTrue(this.isNetworkError) }
        verify(mockedApi, times(1)).discoverMovie()
    }

    @Test
    fun `discover movie list api error`() = runTest {
        val response = ApiResponse.ApiError<NetworkErrorModel>()
        whenever(mockedApi.discoverMovie()).thenReturn(response)

        discoverSource.discoverMovie().run { assertTrue(this.isApiError) }
        verify(mockedApi, times(1)).discoverMovie()
    }

    @Test
    fun `discover movie list unknown error`() = runTest {
        val response = ApiResponse.UnknownError()
        whenever(mockedApi.discoverMovie()).thenReturn(response)

        discoverSource.discoverMovie().run { assertTrue(this.isUnknownError) }
        verify(mockedApi, times(1)).discoverMovie()
    }

    @Test
    fun `discover tv list success`() = runTest {
        val response = Success(
            DataPage(
                page = 1,
                results = listOf(ModelUtil.movieModel),
                totalPages = 1,
                totalResults = 1,
            ),
        )
        whenever(mockedApi.discoverTv()).thenReturn(response)

        discoverSource.discoverTv().run { assertSame(this, response) }
        verify(mockedApi, times(1)).discoverTv()
    }

    @Test
    fun `discover tv list network error`() = runTest {
        val response = ApiResponse.NetworkError()
        whenever(mockedApi.discoverTv()).thenReturn(response)

        discoverSource.discoverTv().run { assertTrue(this.isNetworkError) }
        verify(mockedApi, times(1)).discoverTv()
    }

    @Test
    fun `discover tv list api error`() = runTest {
        val response = ApiResponse.ApiError<NetworkErrorModel>()
        whenever(mockedApi.discoverTv()).thenReturn(response)

        discoverSource.discoverTv().run { assertTrue(this.isApiError) }
        verify(mockedApi, times(1)).discoverTv()
    }

    @Test
    fun `discover tv list unknown error`() = runTest {
        val response = ApiResponse.UnknownError()
        whenever(mockedApi.discoverTv()).thenReturn(response)

        discoverSource.discoverTv().run { assertTrue(this.isUnknownError) }
        verify(mockedApi, times(1)).discoverTv()
    }
}
