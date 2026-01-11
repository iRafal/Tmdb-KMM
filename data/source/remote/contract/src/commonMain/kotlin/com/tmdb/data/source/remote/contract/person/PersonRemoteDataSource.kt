package com.tmdb.data.source.remote.contract.person

import com.tmdb.data.api.model.person.Person
import com.tmdb.data.api.model.util.ApiResponse
import com.tmdb.data.api.model.util.NetworkErrorModel

interface PersonRemoteDataSource {
    suspend fun personDetails(
        personId: Int,
        language: String? = null,
        appendToResponse: String? = null,
    ): ApiResponse<Person, NetworkErrorModel>
}
