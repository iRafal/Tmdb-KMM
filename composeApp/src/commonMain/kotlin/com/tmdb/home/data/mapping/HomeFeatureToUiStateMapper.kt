package com.tmdb.home.data.mapping

import com.tmdb.home.data.HomeMovieSection
import com.tmdb.home.data.HomeUiData
import com.tmdb.store.state.home.HomeFeatureState

typealias HomeFeatureToUiStateMapper = (featureState: HomeFeatureState) -> HomeUiData

fun homeFeatureToUiStateMapperImpl(
    homeFeatureStateToUiSectionStateMapper: HomeFeatureStateToUiSectionStateMapper
): HomeFeatureToUiStateMapper {

    return { featureState ->
        val nowPlayingMoviesState =
            homeFeatureStateToUiSectionStateMapper(featureState.nowPlayingMoviesState)
        val nowPopularMoviesState =
            homeFeatureStateToUiSectionStateMapper(featureState.nowPopularMoviesState)
        val topRatedMoviesState =
            homeFeatureStateToUiSectionStateMapper(featureState.topRatedMoviesState)
        val upcomingMoviesState =
            homeFeatureStateToUiSectionStateMapper(featureState.upcomingMoviesState)

        HomeUiData(
            movieSections = mapOf(
                HomeMovieSection.NOW_PLAYING to nowPlayingMoviesState,
                HomeMovieSection.NOW_POPULAR to nowPopularMoviesState,
                HomeMovieSection.TOP_RATED to topRatedMoviesState,
                HomeMovieSection.UPCOMING to upcomingMoviesState,
            )
        )
    }
}
