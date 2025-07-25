package com.tmdb.data.api.impl.di

import com.tmdb.data.api.model.util.ApiException
import io.ktor.client.HttpClient
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.HttpSend
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.plugin
import io.ktor.client.request.header
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType.Application
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import kotlinx.io.IOException
import kotlinx.serialization.json.Json

private const val TIMEOUT_MILLIS_DEFAULT = 10_000L
private const val PARAMETER_API_KEY = "api_key"

fun json() = Json {
    ignoreUnknownKeys = true
    prettyPrint = true
}

fun createKtorHttpClient(
    apiKey: String,
    logLevel: LogLevel,
    apiErrorMapper: ApiErrorMapper,
    logger: Logger,
    json: Json,
    connectTimeout: Long = TIMEOUT_MILLIS_DEFAULT
): HttpClient = HttpClient {
    install(ContentNegotiation) {
        json(json)
    }

    install(HttpTimeout) {
        requestTimeoutMillis = connectTimeout
    }

    install(DefaultRequest) {
        header(HttpHeaders.ContentType, Application.Json)
    }

    install(Logging) {
        this.logger = logger
        level = logLevel
    }

    HttpResponseValidator {
        handleResponseExceptionWithRequest { cause, request ->
            throw apiErrorMapper(cause)
        }
    }
}.also {
    it.plugin(HttpSend).intercept { request ->
        request.url.parameters.append(PARAMETER_API_KEY, apiKey)
        execute(request)
    }
}

typealias ApiErrorMapper = suspend (cause: Throwable) -> ApiException

fun apiErrorMapper(): ApiErrorMapper = { cause ->
    when (cause) {
        is IOException -> ApiException.NetworkError(cause)
        is ClientRequestException -> {
            val requestException: ClientRequestException = cause
            val statusCode = requestException.response.status
            val responseBody = requestException.response.bodyAsText()
            when (statusCode) {
                HttpStatusCode.InternalServerError -> {
                    ApiException.InternalServerError(requestException, responseBody)
                }
                HttpStatusCode.BadRequest -> {
                    ApiException.BadRequest(requestException, responseBody)
                }
                HttpStatusCode.Unauthorized -> {
                    ApiException.Unauthorized(requestException, responseBody)
                }
                else -> ApiException.HttpError(requestException, responseBody, statusCode.value)
            }
        }
        else -> ApiException.UnknownError(cause)
    }
}

fun ktorLogger(): Logger = object : Logger {
    override fun log(message: String) {
        co.touchlab.kermit.Logger.d { "Ktor Api Log: $message" }
    }
}
