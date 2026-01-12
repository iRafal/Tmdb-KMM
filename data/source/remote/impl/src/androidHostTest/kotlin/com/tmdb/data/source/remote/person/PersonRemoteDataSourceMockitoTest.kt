package com.tmdb.data.source.remote.person

import com.tmdb.data.api.impl.person.PersonApi
import com.tmdb.data.api.model.util.ApiResponse
import com.tmdb.data.api.model.util.ApiResponse.Success
import com.tmdb.data.api.model.util.NetworkErrorModel
import com.tmdb.data.source.remote.contract.person.PersonRemoteDataSource
import com.tmdb.data.source.remote.impl.person.PersonRemoteDataSourceImpl
import com.tmdb.data.source.remote.util.model.ModelUtil
import kotlin.test.Test
import kotlin.test.assertSame
import kotlin.test.assertTrue
import kotlinx.coroutines.test.runTest
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class PersonRemoteDataSourceMockitoTest {

    private val mockedApi: PersonApi = mock()
    private val personSource: PersonRemoteDataSource = PersonRemoteDataSourceImpl(mockedApi)
    private val personId = 287

    @Test
    fun `person details success`() = runTest {
        val response = Success(ModelUtil.personModel)
        whenever(mockedApi.personDetails(personId)).thenReturn(response)

        personSource.personDetails(personId).run { assertSame(this, response) }
        verify(mockedApi, times(1)).personDetails(personId)
    }

    @Test
    fun `person details network error`() = runTest {
        val response = ApiResponse.NetworkError()
        whenever(mockedApi.personDetails(personId)).thenReturn(response)

        personSource.personDetails(personId).run { assertTrue(this.isNetworkError) }
        verify(mockedApi, times(1)).personDetails(personId)
    }

    @Test
    fun `person details api error`() = runTest {
        val response = ApiResponse.ApiError<NetworkErrorModel>()
        whenever(mockedApi.personDetails(personId)).thenReturn(response)

        personSource.personDetails(personId).run { assertTrue(this.isApiError) }
        verify(mockedApi, times(1)).personDetails(personId)
    }

    @Test
    fun `person details unknown error`() = runTest {
        val response = ApiResponse.UnknownError()
        whenever(mockedApi.personDetails(personId)).thenReturn(response)

        personSource.personDetails(personId).run { assertTrue(this.isUnknownError) }
        verify(mockedApi, times(1)).personDetails(personId)
    }
}
