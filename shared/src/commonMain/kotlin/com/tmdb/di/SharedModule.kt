package com.tmdb.di

import com.tmdb.shared.details.MovieDetailsViewModel
import com.tmdb.shared.home.HomeViewModel
import com.tmdb.store.app.di.appStoreModule
import org.koin.dsl.module

val sharedModule = module {
    includes(appStoreModule)
    single { HomeViewModel(get()) }
    single { MovieDetailsViewModel(get()) }
}