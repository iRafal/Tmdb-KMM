package com.tmdb.shared

import com.tmdb.shared.details.SharedMovieDetailsViewModel
import com.tmdb.shared.details.MovieDetailsViewModel
import com.tmdb.shared.di.module.sharedModule
import com.tmdb.shared.home.SharedHomeViewModel
import com.tmdb.shared.home.HomeViewModel
import org.koin.core.KoinApplication
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.context.startKoin

object SharedModule : KoinComponent {
    val sharedHomeViewModel: SharedHomeViewModel
        get() = get()

    val homeViewModel: HomeViewModel
        get() = get()

    val sharedMovieDetailsViewModel: SharedMovieDetailsViewModel
        get() = get()

    val movieDetailsViewModel: MovieDetailsViewModel
        get() = get()

    fun start(additionalConfig: KoinApplication.() -> Unit = {}) {
        startKoin {
            additionalConfig()
            modules(sharedModule())
        }
    }
}