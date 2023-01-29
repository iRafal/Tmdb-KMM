package com.tmdb.ui.shared.home.data.mapping

import com.tmdb.ui.shared.home.data.HomeUiData
import com.tmdb.data.model.movie.MovieDataModel

typealias MovieDataItemsToHomeModelMapper = (input: List<MovieDataModel>) -> List<HomeUiData.Movie>

fun movieDataItemsToHomeModelMapperImpl(
    movieDataToHomeModelMapper: MovieDataToHomeModelMapper
): MovieDataItemsToHomeModelMapper = { input -> input.map { movieDataToHomeModelMapper(it) } }