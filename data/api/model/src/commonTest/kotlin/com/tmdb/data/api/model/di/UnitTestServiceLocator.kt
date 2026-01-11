package com.tmdb.data.api.model.di

import kotlinx.serialization.json.Json

object UnitTestServiceLocator {
    val json = Json {
        ignoreUnknownKeys = true
        prettyPrint = true
    }
}
