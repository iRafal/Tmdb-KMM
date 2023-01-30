package com.tmdb.ui.shared.di

import com.tmdb.ui.shared.details.SharedMovieDetailsViewModel
import com.tmdb.ui.shared.home.SharedHomeViewModel
import com.tmdb.store.app.di.appStoreModule
import com.tmdb.ui.shared.home.data.mapping.di.homeUiDataMappingModule
import com.tmdb.util.dispatcher.di.DISPATCHER_IO
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun sharedModule() = module {
    includes(appStoreModule(), homeUiDataMappingModule())
    single {
        SharedHomeViewModel(
            store = get(),
            homeFeatureToUiStateMapper = get(named("HomeFeatureToUiStateMapper")),
            homeMovieSectionToActionMapper = get(named("HomeMovieSectionToActionMapper")),
            dispatcherIo = get(named(DISPATCHER_IO))
        )
    }
    single { SharedMovieDetailsViewModel(store = get()) }
}