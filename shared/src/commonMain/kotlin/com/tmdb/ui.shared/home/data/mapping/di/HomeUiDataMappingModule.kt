package com.tmdb.ui.shared.home.data.mapping.di

import com.tmdb.ui.shared.home.data.mapping.HomeFeatureStateToUiSectionStateMapper
import com.tmdb.ui.shared.home.data.mapping.HomeFeatureToUiStateMapper
import com.tmdb.ui.shared.home.data.mapping.HomeMovieSectionToActionMapper
import com.tmdb.ui.shared.home.data.mapping.MovieDataItemsToHomeModelMapper
import com.tmdb.ui.shared.home.data.mapping.MovieDataToHomeModelMapper
import com.tmdb.ui.shared.home.data.mapping.homeFeatureToUiStateMapperImpl
import com.tmdb.ui.shared.home.data.mapping.homeMovieSectionToActionMapperImpl
import com.tmdb.ui.shared.home.data.mapping.movieDataItemsToHomeModelMapperImpl
import com.tmdb.ui.shared.home.data.mapping.movieDataToHomeModelMapperImpl
import com.tmdb.ui.shared.core.data.mapping.mapFeatureToUiState
import kotlin.jvm.JvmSuppressWildcards
import org.koin.core.qualifier.named
import org.koin.dsl.module

val homeUiDataMappingModule = module {
    factory<HomeFeatureToUiStateMapper>(named("HomeFeatureToUiStateMapper")) {
        homeFeatureToUiStateMapperImpl(get(named("HomeFeatureStateToUiSectionStateMapper")))
    }

    factory<HomeFeatureStateToUiSectionStateMapper>(named("HomeFeatureStateToUiSectionStateMapper")) {
        mapFeatureToUiState(get(named("MovieDataItemsToHomeModelMapper")))
    }

    factory<MovieDataItemsToHomeModelMapper>(named("MovieDataItemsToHomeModelMapper")) {
        movieDataItemsToHomeModelMapperImpl(get(named("MovieDataToHomeModelMapper")))
    }

    factory<MovieDataToHomeModelMapper>(named("MovieDataToHomeModelMapper")) {
        movieDataToHomeModelMapperImpl()
    }

    factory<HomeMovieSectionToActionMapper>(named("HomeMovieSectionToActionMapper")) {
        ::homeMovieSectionToActionMapperImpl
    }
}