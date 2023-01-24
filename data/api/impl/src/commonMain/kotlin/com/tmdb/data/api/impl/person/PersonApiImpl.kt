package com.tmdb.data.api.impl.person

import com.tmdb.data.api.impl.util.runApiCall
import com.tmdb.data.api.model.person.Person
import com.tmdb.data.api.model.util.ApiResponse
import com.tmdb.data.api.model.util.NetworkErrorModel
import com.tmdb_test.api.config.url.provider.person.PersonUrlProvider
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter


class PersonApiImpl(
    private val client: HttpClient,
    private val urlProvider: PersonUrlProvider,
) : PersonApi {
    override suspend fun personDetails(
        personId: Int,
        language: String?,
        appendToResponse: String?,
    ): ApiResponse<Person, NetworkErrorModel> = runApiCall {
        client.get(urlProvider.personDetailsUrl(personId)) {
            parameter("language", language)
            parameter("append_to_response", appendToResponse)
        }.body()
    }
}