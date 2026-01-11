package com.tmdb.data.source.remote.genre

import com.tmdb.data.api.impl.genre.GenreApi
import com.tmdb.data.api.impl.genre.MockGenreApi
import com.tmdb.data.api.model.genre.Genre
import com.tmdb.data.api.model.util.ApiResponse
import com.tmdb.data.api.model.util.NetworkErrorModel
import com.tmdb.data.source.remote.contract.genre.GenreRemoteDataSource
import com.tmdb.data.source.remote.impl.genre.GenreRemoteDataSourceImpl
import kotlin.test.Test
import kotlin.test.assertSame
import kotlin.test.assertTrue
import kotlinx.coroutines.test.runTest
import org.kodein.mock.Mocker
import org.kodein.mock.UsesMocks

@UsesMocks(GenreApi::class)
class GenreRemoteDataSourceMockLibraryTest {
    private val genreList = listOf(Genre(id = 28, name = "Action"))
    private val successResponse = ApiResponse.Success(genreList)

    @Test
    fun `genre tv list success`() = runTest {
        val mocker = Mocker()
        val mockedApi: GenreApi = MockGenreApi(mocker)
        mocker.everySuspending { mockedApi.genreTvList() } returns successResponse

        val genreSource: GenreRemoteDataSource = GenreRemoteDataSourceImpl(mockedApi)
        genreSource.genreTvList().run { assertSame(this, successResponse) }
        mocker.verifyWithSuspend { called { mockedApi.genreTvList() } }
    }

    @Test
    fun `genre tv list network error`() = runTest {
        val mocker = Mocker()
        val mockedApi: GenreApi = MockGenreApi(mocker)
        val response = ApiResponse.NetworkError()
        mocker.everySuspending { mockedApi.genreTvList() } returns response

        val genreSource: GenreRemoteDataSource = GenreRemoteDataSourceImpl(mockedApi)
        genreSource.genreTvList().run { assertTrue(this.isNetworkError) }
        mocker.verifyWithSuspend { called { mockedApi.genreTvList() } }
    }

    @Test
    fun `genre tv list api error`() = runTest {
        val mocker = Mocker()
        val mockedApi: GenreApi = MockGenreApi(mocker)
        val response = ApiResponse.ApiError<NetworkErrorModel>()
        mocker.everySuspending { mockedApi.genreTvList() } returns response

        val genreSource: GenreRemoteDataSource = GenreRemoteDataSourceImpl(mockedApi)
        genreSource.genreTvList().run { assertTrue(this.isApiError) }
        mocker.verifyWithSuspend { called { mockedApi.genreTvList() } }
    }

    @Test
    fun `genre tv list unknown error`() = runTest {
        val mocker = Mocker()
        val mockedApi: GenreApi = MockGenreApi(mocker)
        val response = ApiResponse.UnknownError()
        mocker.everySuspending { mockedApi.genreTvList() } returns response

        val genreSource: GenreRemoteDataSource = GenreRemoteDataSourceImpl(mockedApi)
        genreSource.genreTvList().run { assertTrue(this.isUnknownError) }
        mocker.verifyWithSuspend { called { mockedApi.genreTvList() } }
    }

    @Test
    fun `genre movie list success`() = runTest {
        val mocker = Mocker()
        val mockedApi: GenreApi = MockGenreApi(mocker)
        mocker.everySuspending { mockedApi.genreMovieList() } returns successResponse

        val genreSource: GenreRemoteDataSource = GenreRemoteDataSourceImpl(mockedApi)
        genreSource.genreMovieList().run { assertSame(this, successResponse) }
        mocker.verifyWithSuspend { called { mockedApi.genreMovieList() } }
    }

    @Test
    fun `genre movie list network error`() = runTest {
        val mocker = Mocker()
        val mockedApi: GenreApi = MockGenreApi(mocker)
        val response = ApiResponse.NetworkError()
        mocker.everySuspending { mockedApi.genreMovieList() } returns response

        val genreSource: GenreRemoteDataSource = GenreRemoteDataSourceImpl(mockedApi)
        genreSource.genreMovieList().run { assertTrue(this.isNetworkError) }
        mocker.verifyWithSuspend { called { mockedApi.genreMovieList() } }
    }

    @Test
    fun `genre movie list api error`() = runTest {
        val mocker = Mocker()
        val mockedApi: GenreApi = MockGenreApi(mocker)
        val response = ApiResponse.ApiError<NetworkErrorModel>()
        mocker.everySuspending { mockedApi.genreMovieList() } returns response

        val genreSource: GenreRemoteDataSource = GenreRemoteDataSourceImpl(mockedApi)
        genreSource.genreMovieList().run { assertTrue(this.isApiError) }
        mocker.verifyWithSuspend { called { mockedApi.genreMovieList() } }
    }

    @Test
    fun `genre movie list unknown error`() = runTest {
        val mocker = Mocker()
        val mockedApi: GenreApi = MockGenreApi(mocker)
        val response = ApiResponse.UnknownError()
        mocker.everySuspending { mockedApi.genreMovieList() } returns response

        val genreSource: GenreRemoteDataSource = GenreRemoteDataSourceImpl(mockedApi)
        genreSource.genreMovieList().run { assertTrue(this.isUnknownError) }
        mocker.verifyWithSuspend { called { mockedApi.genreMovieList() } }
    }
}
