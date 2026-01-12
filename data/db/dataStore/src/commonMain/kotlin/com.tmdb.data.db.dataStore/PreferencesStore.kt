package com.tmdb.data.db.dataStore

import kotlinx.coroutines.flow.Flow

interface PreferencesStore {
    val token: Flow<String?>
    suspend fun setToken(token: String)
    suspend fun removeToken()
}

internal const val KEY_TOKEN = "KEY_TOKEN"
