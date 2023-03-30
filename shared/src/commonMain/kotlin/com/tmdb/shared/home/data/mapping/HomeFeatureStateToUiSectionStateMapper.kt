package com.tmdb.shared.home.data.mapping

import com.tmdb.data.model.movie.MovieDataModel
import com.tmdb.shared.home.data.HomeUiData.Movie
import com.tmdb.shared.core.data.mapping.FeatureToUiStateMapper

typealias HomeFeatureStateToUiSectionStateMapper =
        FeatureToUiStateMapper<List<MovieDataModel>, List<Movie>>