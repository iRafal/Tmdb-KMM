package com.tmdb.ui.shared.home.data.mapping

import com.tmdb.ui.shared.home.data.HomeUiData.Movie
import com.tmdb.data.model.movie.MovieDataModel
import com.tmdb.ui.shared.core.data.mapping.FeatureToUiStateMapper

typealias HomeFeatureStateToUiSectionStateMapper =
        FeatureToUiStateMapper<List<MovieDataModel>, List<Movie>>