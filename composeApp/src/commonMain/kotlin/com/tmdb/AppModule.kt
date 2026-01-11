package com.tmdb

import com.tmdb.data.db.dataStore.di.module.preferencesStoreModule
import com.tmdb.data.db.multiplatformSettings.di.module.settingsDataStorageModule
import com.tmdb.module.sharedModule
import kotlin.experimental.ExperimentalObjCName
import kotlin.native.ObjCName
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

@OptIn(ExperimentalObjCName::class)
@ObjCName("AppModule")
object AppModule : KoinComponent {
    fun start(appDeclaration: KoinAppDeclaration = {}) = startKoin {
        appDeclaration()
        modules(sharedModule(), preferencesStoreModule(), settingsDataStorageModule())
    }

    fun startIos() {
        start()
    }
}
