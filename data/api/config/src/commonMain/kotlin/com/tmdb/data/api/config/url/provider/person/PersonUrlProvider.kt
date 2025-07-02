package com.tmdb.data.api.config.url.provider.person

fun interface PersonUrlProvider {
    fun personDetailsUrl(personId: Int): String
}
