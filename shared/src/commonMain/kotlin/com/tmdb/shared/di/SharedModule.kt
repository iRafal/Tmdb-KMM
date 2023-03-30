package com.tmdb.shared.di

import com.tmdb.shared.details.SharedMovieDetailsViewModel
import com.tmdb.shared.home.SharedHomeViewModel
import com.tmdb.store.app.di.appStoreModule
import com.tmdb.shared.home.data.mapping.di.homeUiDataMappingModule
import com.tmdb.util.dispatcher.di.DISPATCHER_IO
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun sharedModule() = module {
    includes(appStoreModule(), homeUiDataMappingModule())
    factory {
        SharedHomeViewModel(
            store = get(),
            homeFeatureToUiStateMapper = get(named("HomeFeatureToUiStateMapper")),
            homeMovieSectionToActionMapper = get(named("HomeMovieSectionToActionMapper")),
            dispatcherIo = get(named(DISPATCHER_IO))
        )
    }
    factory { SharedMovieDetailsViewModel(store = get()) }
}