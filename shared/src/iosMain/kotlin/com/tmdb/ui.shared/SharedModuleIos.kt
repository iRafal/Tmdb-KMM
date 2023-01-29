package com.tmdb.ui.shared

import com.tmdb.ui.shared.di.sharedModule
import org.koin.core.context.startKoin

class SharedModuleIos {
    fun init() {
        startKoin {
            modules(sharedModule)
        }
    }
}