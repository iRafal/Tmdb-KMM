package com.tmdb.store.reducer.di

import com.tmdb.data.model.di.movieApiMappingModule
import com.tmdb.data.model.mapping.movie.MoviesApiToDataStateMapper
import com.tmdb.store.reducer.home.HomeFeatureEffects
import com.tmdb.store.reducer.home.HomeFeatureSlice
import com.tmdb.store.reducer.home.HomeFeatureSliceImpl
import com.tmdb.store.state.di.homeFeatureMappingModule
import com.tmdb.util.di.DISPATCHER_IO
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.named
import org.koin.dsl.module

val homeFeatureModule = module {
    includes(movieApiMappingModule, homeFeatureMappingModule)
    single<HomeFeatureSlice> {
        HomeFeatureSliceImpl(
            moviesApiToDataStateMapper = get(named("MoviesApiToDataStateMapper")),
            moviesDataToFeatureStateMapper = get(named("MoviesDataToFeatureStateMapper")),
            homeFeatureEffects = get()
        )
    }
    single { HomeFeatureEffects(dispatcher = get(named(DISPATCHER_IO))) }
}