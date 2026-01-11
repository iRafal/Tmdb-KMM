package com.tmdb.data.source.remote.discover

import com.tmdb.data.api.impl.discover.DiscoverApi
import com.tmdb.data.api.impl.discover.MockDiscoverApi
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
import org.kodein.mock.Mocker
import org.kodein.mock.UsesMocks

@UsesMocks(DiscoverApi::class)
class DiscoverRemoteDataSourceMockLibraryTest {
    @Test
    fun `discover movie list success`() = runTest {
        val mocker = Mocker()
        val mockedApi: DiscoverApi = MockDiscoverApi(mocker)
        val response = Success(
            DataPage(
                page = 1,
                results = listOf(ModelUtil.movieModel),
                totalPages = 1,
                totalResults = 1,
            ),
        )
        mocker.everySuspending { mockedApi.discoverMovie() } returns response

        val discoverSource: DiscoverRemoteDataSource = DiscoverRemoteDataSourceImpl(mockedApi)
        discoverSource.discoverMovie().run { assertSame(response, this) }
        mocker.verifyWithSuspend { called { mockedApi.discoverMovie() } }
    }

    @Test
    fun `discover movie list network error`() = runTest {
        val mocker = Mocker()
        val mockedApi: DiscoverApi = MockDiscoverApi(mocker)
        val response = ApiResponse.NetworkError()
        mocker.everySuspending { mockedApi.discoverMovie() } returns response

        val discoverSource: DiscoverRemoteDataSource = DiscoverRemoteDataSourceImpl(mockedApi)
        discoverSource.discoverMovie().run { assertTrue(this.isNetworkError) }
        mocker.verifyWithSuspend { called { mockedApi.discoverMovie() } }
    }

    @Test
    fun `discover movie list api error`() = runTest {
        val mocker = Mocker()
        val mockedApi: DiscoverApi = MockDiscoverApi(mocker)
        val response = ApiResponse.ApiError<NetworkErrorModel>()
        mocker.everySuspending { mockedApi.discoverMovie() } returns response

        val discoverSource: DiscoverRemoteDataSource = DiscoverRemoteDataSourceImpl(mockedApi)
        discoverSource.discoverMovie().run { assertTrue(this.isApiError) }
        mocker.verifyWithSuspend { called { mockedApi.discoverMovie() } }
    }

    @Test
    fun `discover movie list unknown error`() = runTest {
        val mocker = Mocker()
        val mockedApi: DiscoverApi = MockDiscoverApi(mocker)
        val response = ApiResponse.UnknownError()
        mocker.everySuspending { mockedApi.discoverMovie() } returns response

        val discoverSource: DiscoverRemoteDataSource = DiscoverRemoteDataSourceImpl(mockedApi)
        discoverSource.discoverMovie().run { assertTrue(this.isUnknownError) }
        mocker.verifyWithSuspend { called { mockedApi.discoverMovie() } }
    }

    @Test
    fun `discover tv list success`() = runTest {
        val mocker = Mocker()
        val mockedApi: DiscoverApi = MockDiscoverApi(mocker)
        val response = Success(
            DataPage(
                page = 1,
                results = listOf(ModelUtil.movieModel),
                totalPages = 1,
                totalResults = 1,
            ),
        )
        mocker.everySuspending { mockedApi.discoverTv() } returns response

        val discoverSource: DiscoverRemoteDataSource = DiscoverRemoteDataSourceImpl(mockedApi)
        discoverSource.discoverTv().run { assertSame(this, response) }
        mocker.verifyWithSuspend { called { mockedApi.discoverTv() } }
    }

    @Test
    fun `discover tv list network error`() = runTest {
        val mocker = Mocker()
        val mockedApi: DiscoverApi = MockDiscoverApi(mocker)
        val response = ApiResponse.NetworkError()
        mocker.everySuspending { mockedApi.discoverTv() } returns response

        val discoverSource: DiscoverRemoteDataSource = DiscoverRemoteDataSourceImpl(mockedApi)
        discoverSource.discoverTv().run { assertTrue(this.isNetworkError) }
        mocker.verifyWithSuspend { called { mockedApi.discoverTv() } }
    }

    @Test
    fun `discover tv list api error`() = runTest {
        val mocker = Mocker()
        val mockedApi: DiscoverApi = MockDiscoverApi(mocker)
        val response = ApiResponse.ApiError<NetworkErrorModel>()
        mocker.everySuspending { mockedApi.discoverTv() } returns response

        val discoverSource: DiscoverRemoteDataSource = DiscoverRemoteDataSourceImpl(mockedApi)
        discoverSource.discoverTv().run { assertTrue(this.isApiError) }
        mocker.verifyWithSuspend { called { mockedApi.discoverTv() } }
    }

    @Test
    fun `discover tv list unknown error`() = runTest {
        val mocker = Mocker()
        val mockedApi: DiscoverApi = MockDiscoverApi(mocker)
        val response = ApiResponse.UnknownError()
        mocker.everySuspending { mockedApi.discoverTv() } returns response

        val discoverSource: DiscoverRemoteDataSource = DiscoverRemoteDataSourceImpl(mockedApi)
        discoverSource.discoverTv().run { assertTrue(this.isUnknownError) }
        mocker.verifyWithSuspend { called { mockedApi.discoverTv() } }
    }
}
