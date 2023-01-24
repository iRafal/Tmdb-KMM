package com.tmdb.data.api.config.url.provider.person

import com.tmdb_test.api.config.url.provider.person.PersonUrlProvider

class PersonUrlProviderImpl constructor(
    private val personBaseUrl: String
) : PersonUrlProvider {
    override fun personDetailsUrl(personId: Int): String = "${personBaseUrl}person/$personId}"
}