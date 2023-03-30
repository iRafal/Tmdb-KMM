package com.tmdb.shared.home.data.mapping

import com.tmdb.data.model.movie.MovieDataModel
import com.tmdb.shared.home.data.HomeUiData

typealias MovieDataItemsToHomeModelMapper = (input: List<MovieDataModel>) -> List<HomeUiData.Movie>

fun movieDataItemsToHomeModelMapperImpl(
    movieDataToHomeModelMapper: MovieDataToHomeModelMapper
): MovieDataItemsToHomeModelMapper = { input -> input.map { movieDataToHomeModelMapper(it) } }