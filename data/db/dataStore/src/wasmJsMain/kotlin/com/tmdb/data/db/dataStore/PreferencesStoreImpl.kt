package com.tmdb.data.db.dataStore

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

internal class PreferencesStoreImpl() : PreferencesStore {
    private val tokenInternal = MutableStateFlow<String?>(null)

    override val token: Flow<String?> = tokenInternal

    override suspend fun setToken(token: String) {
        tokenInternal.value = token
    }

    override suspend fun removeToken() {
        tokenInternal.value = null
    }
}
