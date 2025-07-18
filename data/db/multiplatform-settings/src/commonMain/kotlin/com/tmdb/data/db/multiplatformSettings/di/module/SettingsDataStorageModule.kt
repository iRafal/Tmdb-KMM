package com.tmdb.data.db.multiplatformSettings.di.module

import com.tmdb.data.db.multiplatformSettings.SettingsStorage
import com.tmdb.data.db.multiplatformSettings.SettingsStorageImpl
import org.koin.core.module.Module
import org.koin.dsl.module

fun settingsDataStorageModule(): Module = module {
    factory<SettingsStorage> { SettingsStorageImpl(get()) }
    includes(multiplatformSettingsModule())
}
