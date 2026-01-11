package com.tmdb.data.db.multiplatformSettings

import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.Settings
import com.russhwolf.settings.coroutines.FlowSettings
import kotlinx.coroutines.flow.Flow

interface SettingsStorage {
    val token: Flow<String?>

    suspend fun setToken(token: String)
    suspend fun removeToken()
}

private const val KEY_TOKEN = "KEY_TOKEN"

class SettingsStorageImpl(private val settings: Settings) : SettingsStorage {
    @OptIn(ExperimentalSettingsApi::class)
    val flowSettings: FlowSettings
        get() = settings as FlowSettings

    @OptIn(ExperimentalSettingsApi::class)
    override val token: Flow<String?>
        get() = flowSettings.getStringOrNullFlow(KEY_TOKEN)

    override suspend fun setToken(token: String) {
        settings.putString(KEY_TOKEN, token)
    }

    override suspend fun removeToken() {
        settings.remove(KEY_TOKEN)
    }
}
