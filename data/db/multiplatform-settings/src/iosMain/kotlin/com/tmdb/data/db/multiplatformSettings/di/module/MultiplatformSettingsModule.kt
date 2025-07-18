package com.tmdb.data.db.multiplatformSettings.di.module

import com.russhwolf.settings.ExperimentalSettingsImplementation
import com.russhwolf.settings.KeychainSettings
import com.russhwolf.settings.Settings
import org.koin.dsl.module

@OptIn(ExperimentalSettingsImplementation::class)
actual fun multiplatformSettingsModule() = module {
    single<Settings> { KeychainSettings(service = "TOKEN_STORAGE") }
}
