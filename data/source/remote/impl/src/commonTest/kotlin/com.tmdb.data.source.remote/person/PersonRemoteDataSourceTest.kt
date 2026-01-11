package com.tmdb.data.source.remote.person

import com.tmdb.data.api.impl.person.MockPersonApi
import com.tmdb.data.api.impl.person.PersonApi
import com.tmdb.data.api.model.person.Person
import com.tmdb.data.api.model.util.ApiResponse
import com.tmdb.data.api.model.util.NetworkErrorModel
import com.tmdb.data.source.remote.contract.person.PersonRemoteDataSource
import com.tmdb.data.source.remote.impl.person.PersonRemoteDataSourceImpl
import com.tmdb.data.source.remote.util.model.ModelUtil
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertSame
import kotlin.test.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.kodein.mock.Mocker

@ExperimentalCoroutinesApi
class PersonRemoteDataSourceTest {
    private val personId = 287

    @Test
    fun `person details success`() = runTest {
        val response = ApiResponse.Success(ModelUtil.personModel)

        var methodCallingCounter = 0
        val mockApi = object : PersonApi {
            override suspend fun personDetails(
                personId: Int,
                language: String?,
                appendToResponse: String?,
            ): ApiResponse<Person, NetworkErrorModel> {
                methodCallingCounter++
                return response
            }
        }
        val personSource: PersonRemoteDataSource = PersonRemoteDataSourceImpl(mockApi)
        personSource.personDetails(personId).run { assertSame(this, response) }
        assertEquals(
            expected = methodCallingCounter,
            actual = 1,
            message = "PersonApi::personDetails need to call 1 time",
        )
    }

    @Test
    fun `person details success mockLibrary`() = runTest {
        val mocker = Mocker()

        val mockApi: PersonApi = MockPersonApi(mocker)
        val response = ApiResponse.Success(ModelUtil.personModel)
        mocker.everySuspending { mockApi.personDetails(personId) } returns response

        val personSource: PersonRemoteDataSource = PersonRemoteDataSourceImpl(mockApi)
        personSource.personDetails(personId).run { assertSame(this, response) }
        mocker.verifyWithSuspend { called { mockApi.personDetails(personId) } }
    }

    @Test
    fun `person details network error`() = runTest {
        var methodCallingCounter = 0
        val mockApi = object : PersonApi {
            override suspend fun personDetails(
                personId: Int,
                language: String?,
                appendToResponse: String?,
            ): ApiResponse<Person, NetworkErrorModel> {
                methodCallingCounter++
                return ApiResponse.NetworkError()
            }
        }
        val personSource: PersonRemoteDataSource = PersonRemoteDataSourceImpl(mockApi)
        personSource.personDetails(personId).run { assertTrue(this.isNetworkError) }
        assertEquals(
            expected = methodCallingCounter,
            actual = 1,
            message = "PersonApi::personDetails need to call 1 time",
        )
    }

    @Test
    fun `person details network error mockLibrary`() = runTest {
        val mocker = Mocker()
        val mockApi: PersonApi = MockPersonApi(mocker)
        val response = ApiResponse.NetworkError()
        mocker.everySuspending { mockApi.personDetails(personId) } returns response

        val personSource: PersonRemoteDataSource = PersonRemoteDataSourceImpl(mockApi)
        personSource.personDetails(personId).run { assertTrue(this.isNetworkError) }
        mocker.verifyWithSuspend { called { mockApi.personDetails(personId) } }
    }

    @Test
    fun `person details api error`() = runTest {
        var methodCallingCounter = 0
        val mockApi = object : PersonApi {
            override suspend fun personDetails(
                personId: Int,
                language: String?,
                appendToResponse: String?,
            ): ApiResponse<Person, NetworkErrorModel> {
                methodCallingCounter++
                return ApiResponse.ApiError()
            }
        }
        val personSource: PersonRemoteDataSource = PersonRemoteDataSourceImpl(mockApi)
        personSource.personDetails(personId).run { assertTrue(this.isApiError) }
        assertEquals(
            expected = methodCallingCounter,
            actual = 1,
            message = "PersonApi::personDetails need to call 1 time",
        )
    }

    @Test
    fun `person details api error mockLibrary`() = runTest {
        val mocker = Mocker()
        val mockApi: PersonApi = MockPersonApi(mocker)
        val response = ApiResponse.ApiError<NetworkErrorModel>()
        mocker.everySuspending { mockApi.personDetails(personId) } returns response

        val personSource: PersonRemoteDataSource = PersonRemoteDataSourceImpl(mockApi)
        personSource.personDetails(personId).run { assertTrue(this.isApiError) }
        mocker.verifyWithSuspend { called { mockApi.personDetails(personId) } }
    }

    @Test
    fun `person details unknown error`() = runTest {
        var methodCallingCounter = 0
        val mockApi = object : PersonApi {
            override suspend fun personDetails(
                personId: Int,
                language: String?,
                appendToResponse: String?,
            ): ApiResponse<Person, NetworkErrorModel> {
                methodCallingCounter++
                return ApiResponse.UnknownError()
            }
        }
        val personSource: PersonRemoteDataSource = PersonRemoteDataSourceImpl(mockApi)
        personSource.personDetails(personId).run { assertTrue(this.isUnknownError) }
        assertEquals(
            expected = methodCallingCounter,
            actual = 1,
            message = "PersonApi::personDetails need to call 1 time",
        )
    }

    @Test
    fun `person details unknown error mockLibrary`() = runTest {
        val mocker = Mocker()
        val mockApi: PersonApi = MockPersonApi(mocker)
        val response = ApiResponse.UnknownError()
        mocker.everySuspending { mockApi.personDetails(personId) } returns response

        val personSource: PersonRemoteDataSource = PersonRemoteDataSourceImpl(mockApi)
        personSource.personDetails(personId).run { assertTrue(this.isUnknownError) }
        mocker.verifyWithSuspend { called { mockApi.personDetails(personId) } }
    }
}
