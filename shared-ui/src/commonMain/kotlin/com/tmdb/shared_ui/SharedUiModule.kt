package com.tmdb.shared_ui

import com.tmdb.shared.details.MovieDetailsViewModel
import com.tmdb.shared_ui.di.module.sharedUiModule
import com.tmdb.shared.home.HomeViewModel
import org.koin.core.KoinApplication
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.context.startKoin

object SharedUiModule : KoinComponent {
    val homeViewModel: HomeViewModel
        get() = get()

    val movieDetailsViewModel: MovieDetailsViewModel
        get() = get()

    fun start(additionalConfig: KoinApplication.() -> Unit = {}) {
        startKoin {
            additionalConfig()
            modules(sharedUiModule())
        }
    }
}