package com.tmdb.shared_ui.di.module

import com.tmdb.shared.di.module.sharedModule
import com.tmdb.shared_ui.details.MovieDetailsViewModel
import com.tmdb.shared_ui.home.HomeViewModel
import org.koin.dsl.module


fun sharedUiModule() = module {
    includes(sharedModule())
    factory { HomeViewModel(get()) }
    factory { MovieDetailsViewModel(get()) }
}