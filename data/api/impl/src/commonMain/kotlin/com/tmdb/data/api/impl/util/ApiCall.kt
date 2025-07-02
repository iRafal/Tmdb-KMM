package com.tmdb.data.api.impl.util

import com.tmdb.data.api.model.util.ApiException
import com.tmdb.data.api.model.util.ApiResponse

suspend fun <T: Any, U: Any> runApiCall(apiCall: suspend () -> T): ApiResponse<T, U> {
    return try {
        ApiResponse.Success(apiCall.invoke())
    } catch (e: Throwable) {
        when (e) {
            is ApiException.NetworkError -> ApiResponse.NetworkError(e)
            else -> ApiResponse.UnknownError(e)
        }
    }
}
