package com.tmdb.data.db.multiplatformSettings.di.module

import com.russhwolf.settings.Settings
import com.russhwolf.settings.PreferencesSettings
import org.koin.dsl.module
import java.util.prefs.Preferences

actual fun multiplatformSettingsModule() = module {
    factory<Settings> {
        val prefs = Preferences.userRoot().node("Token_Storage")
        PreferencesSettings(prefs)
    }
}
