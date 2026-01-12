package com.tmdb.data.db.dataStore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class PreferencesStoreImpl(private val dataStore: DataStore<Preferences>) : PreferencesStore {
    override val token: Flow<String?> = dataStore.data.map { preferences ->
        preferences[stringPreferencesKey(KEY_TOKEN)]
    }

    override suspend fun setToken(token: String) {
        dataStore.updateData { preferences ->
            preferences.toMutablePreferences().apply {
                set(stringPreferencesKey(KEY_TOKEN), token)
            }
        }
    }

    override suspend fun removeToken() {
        dataStore.updateData { preferences ->
            preferences.toMutablePreferences().apply {
                remove(stringPreferencesKey(KEY_TOKEN))
            }
        }
    }
}
