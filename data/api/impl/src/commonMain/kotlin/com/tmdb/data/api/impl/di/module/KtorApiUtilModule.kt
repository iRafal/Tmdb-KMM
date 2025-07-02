package com.tmdb.data.api.impl.di.module

import com.tmdb.data.api.config.DataApiConfigBuildKonfig
import com.tmdb.data.api.impl.di.ApiErrorMapper
import com.tmdb.data.api.impl.di.apiErrorMapper
import com.tmdb.data.api.impl.di.createKtorHttpClient
import com.tmdb.data.api.impl.di.json
import com.tmdb.data.api.impl.di.ktorLogger
import io.ktor.client.HttpClient
import io.ktor.client.plugins.logging.LogLevel.BODY
import io.ktor.client.plugins.logging.Logger
import org.koin.dsl.module

fun ktorApiUtilModule() = module {

    single { json() }

    single<ApiErrorMapper> { apiErrorMapper() }

    single<Logger> { ktorLogger() }

    single<HttpClient> {
        createKtorHttpClient(
            apiKey = DataApiConfigBuildKonfig.API_KEY,
            logLevel = BODY,
            apiErrorMapper = get(),
            logger = get(),
            json = get(),
        )
    }
}
