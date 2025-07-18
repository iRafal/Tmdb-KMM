package com.tmdb.data.db.multiplatformSettings.di.module

import android.content.Context
import android.content.SharedPreferences
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

actual fun multiplatformSettingsModule() = module {
    factory<Settings> {
        val context: Context = androidContext()
        val delegate: SharedPreferences = context.getSharedPreferences("Token_Storage", Context.MODE_PRIVATE)
        SharedPreferencesSettings(delegate)
    }
}
