package com.tmdb.ui.shared

import com.tmdb.ui.shared.details.SharedMovieDetailsViewModel
import com.tmdb.ui.shared.di.sharedModule
import com.tmdb.ui.shared.home.SharedHomeViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin

object SharedModule: KoinComponent {
    init {
        startKoin {
            modules(sharedModule())
        }
    }

    val sharedHomeViewModel: SharedHomeViewModel by inject()
    val sharedMovieDetailsViewModel: SharedMovieDetailsViewModel by inject()
}