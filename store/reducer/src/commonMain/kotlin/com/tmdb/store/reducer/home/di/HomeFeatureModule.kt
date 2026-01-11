package com.tmdb.store.reducer.home.di

import com.tmdb.data.model.di.movieApiMappingModule
import com.tmdb.store.reducer.home.HomeFeatureEffects
import com.tmdb.store.reducer.home.HomeFeatureSlice
import com.tmdb.store.reducer.home.HomeFeatureSliceImpl
import com.tmdb.store.state.mapping.di.homeFeatureMappingModule
import com.tmdb.util.dispatcher.di.DISPATCHER_IO
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun homeFeatureModule() = module {
    includes(movieApiMappingModule(), homeFeatureMappingModule())
    single<HomeFeatureSlice> {
        HomeFeatureSliceImpl(
            moviesApiToDataStateMapper = get(named("MoviesApiToDataStateMapper")),
            moviesDataToFeatureStateMapper = get(named("MoviesDataToFeatureStateMapper")),
            homeFeatureEffects = get(),
        )
    }
    single { HomeFeatureEffects(dispatcher = get(named(DISPATCHER_IO))) }
}
