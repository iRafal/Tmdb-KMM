package com.tmdb.data.db.multiplatformSettings.di.module

import com.russhwolf.settings.PreferencesSettings
import com.russhwolf.settings.Settings
import java.util.prefs.Preferences
import org.koin.dsl.module

actual fun multiplatformSettingsModule() = module {
    factory<Settings> {
        val prefs = Preferences.userRoot().node("Token_Storage")
        PreferencesSettings(prefs)
    }
}
