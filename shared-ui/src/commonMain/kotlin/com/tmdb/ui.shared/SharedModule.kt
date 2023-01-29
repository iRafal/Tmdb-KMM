package com.tmdb.ui.shared

import com.tmdb.ui.shared.di.sharedModule
import org.koin.core.context.startKoin

object SharedModule {
    fun init() {
        startKoin {
            modules(sharedModule)
        }
    }
}