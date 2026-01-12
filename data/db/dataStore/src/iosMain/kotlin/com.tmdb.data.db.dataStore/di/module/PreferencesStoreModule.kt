package com.tmdb.data.db.dataStore.di.module

import com.tmdb.data.db.dataStore.PreferencesStore
import com.tmdb.data.db.dataStore.createDataStore
import org.koin.dsl.module

actual fun preferencesStoreModule() = module {
    factory<PreferencesStore> {
        PreferencesStoreImpl(createDataStore())
    }
}
