package com.tmdb.shared.di.module

import com.tmdb.shared.details.MovieDetailsViewModel
import com.tmdb.shared.home.HomeViewModel
import com.tmdb.shared.home.data.mapping.di.homeUiDataMappingModule
import com.tmdb.store.app.di.appStoreModule
import com.tmdb.util.dispatcher.di.DISPATCHER_IO
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun sharedModule() = module {
    includes(appStoreModule(), homeUiDataMappingModule())
    factory {
        HomeViewModel(
            store = get(),
            homeFeatureToUiStateMapper = get(named("HomeFeatureToUiStateMapper")),
            homeMovieSectionToActionMapper = get(named("HomeMovieSectionToActionMapper")),
            dispatcherIo = get(named(DISPATCHER_IO)),
        )
    }
    factory { MovieDetailsViewModel(get()) }
}
