package com.tmdb.shared

import com.tmdb.data.db.dataStore.di.module.preferencesStoreModule
import com.tmdb.shared.di.module.sharedModule
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import kotlin.experimental.ExperimentalObjCName
import kotlin.native.ObjCName

@OptIn(ExperimentalObjCName::class)
@ObjCName("AppModule")
object AppModule : KoinComponent {
    fun start(appDeclaration: KoinAppDeclaration = {}) = startKoin {
        appDeclaration()
        modules(sharedModule(), preferencesStoreModule())
    }

    fun startIos() {
        start()
    }
}
