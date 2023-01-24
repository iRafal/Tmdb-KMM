package com.tmdb.data.source.remote.person

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

@OptIn(ExperimentalCoroutinesApi::class)
@ExperimentalCoroutinesApi
class PersonRemoteDataSourceTest {
    private val personId = 287

    @Test
    fun `person details success`() = runTest {
        val response = ApiResponse.Success(ModelUtil.personModel)

        var methodCallingCounter = 0
        val mockApi = object: PersonApi {
            override suspend fun personDetails(
                personId: Int,
                language: String?,
                appendToResponse: String?
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
            message = "PersonApi::personDetails need to call 1 time"
        )
    }

    @Test
    fun `person details network error`() = runTest {
        var methodCallingCounter = 0
        val mockApi = object: PersonApi {
            override suspend fun personDetails(
                personId: Int,
                language: String?,
                appendToResponse: String?
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
            message = "PersonApi::personDetails need to call 1 time"
        )
    }

    @Test
    fun `person details api error`() = runTest {
        var methodCallingCounter = 0
        val mockApi = object: PersonApi {
            override suspend fun personDetails(
                personId: Int,
                language: String?,
                appendToResponse: String?
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
            message = "PersonApi::personDetails need to call 1 time"
        )
    }

    @Test
    fun `person details unknown error`() = runTest {
        var methodCallingCounter = 0
        val mockApi = object: PersonApi {
            override suspend fun personDetails(
                personId: Int,
                language: String?,
                appendToResponse: String?
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
            message = "PersonApi::personDetails need to call 1 time"
        )
    }
}