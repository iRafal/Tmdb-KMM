package com.tmdb.home.data.mapping

import com.tmdb.data.model.movie.MovieDataModel
import com.tmdb.core.data.mapping.FeatureToUiStateMapper
import com.tmdb.home.data.HomeUiData.Movie

typealias HomeFeatureStateToUiSectionStateMapper = FeatureToUiStateMapper<List<MovieDataModel>, List<Movie>>
