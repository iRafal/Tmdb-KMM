package com.tmdb.store.state.mapping.di

import com.tmdb.store.state.home.MoviesDataToFeatureStateMapper
import com.tmdb.store.state.mapping.mapDataToFeatureStateImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun homeFeatureMappingModule() = module {
    single<MoviesDataToFeatureStateMapper>(named("MoviesDataToFeatureStateMapper")) { ::mapDataToFeatureStateImpl }
}