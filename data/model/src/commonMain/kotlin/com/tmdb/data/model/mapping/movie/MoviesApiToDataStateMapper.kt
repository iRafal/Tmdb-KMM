package com.tmdb.data.model.mapping.movie

import com.tmdb.data.api.model.data.DataPage
import com.tmdb.data.api.model.movie.Movie
import com.tmdb.data.api.model.util.ApiResponse
import com.tmdb.data.api.model.util.NetworkErrorModel
import com.tmdb.data.model.state.DataState
import com.tmdb.data.model.movie.MovieDataModel
import com.tmdb.data.model.mapping.mapApiToDataState

typealias MoviesApiToDataStateMapper = (
    input: ApiResponse<DataPage<Movie>, NetworkErrorModel>
) -> DataState<List<MovieDataModel>>

fun moviesApiToDataStateMapperImpl(
    movieApiToDataModelMapper: MovieApiToDataModelMapper,
): MoviesApiToDataStateMapper {
    return { input: ApiResponse<DataPage<Movie>, NetworkErrorModel> ->
        val dataMapper: (DataPage<Movie>) -> List<MovieDataModel> = { dataPage ->
            dataPage.results.map { movieApiToDataModelMapper(it) }
        }
        input.mapApiToDataState(dataMapper)
    }
}
