package com.tmdb.home.data.mapping

import com.tmdb.home.data.HomeMovieSection
import com.tmdb.store.action.home.HomeAction

typealias HomeMovieSectionToActionMapper = (input: HomeMovieSection) -> HomeAction

fun homeMovieSectionToActionMapperImpl(input: HomeMovieSection): HomeAction = when (input) {
    HomeMovieSection.NOW_PLAYING -> HomeAction.ReloadNowPlayingMovies
    HomeMovieSection.NOW_POPULAR -> HomeAction.ReloadNowPopularMovies
    HomeMovieSection.TOP_RATED -> HomeAction.ReloadTopRatedMovies
    HomeMovieSection.UPCOMING -> HomeAction.ReloadUpcomingMovies
}
