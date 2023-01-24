package com.tmdb.di

import org.koin.core.context.startKoin

object DiKoinController {
    fun initKoin() {
        startKoin {
            modules(sharedModule)
        }
    }
}