package com.tmdb.data.model.mopping.movie

import com.tmdb.data.api.config.url.image.contract.ImageUrlProvider
import com.tmdb.data.api.config.url.image.impl.ImageUrlProviderImpl
import com.tmdb.data.api.model.data.DataPage
import com.tmdb.data.api.model.movie.Movie
import com.tmdb.data.api.model.util.ApiException.NetworkError
import com.tmdb.data.api.model.util.ApiResponse
import com.tmdb.data.api.model.util.ApiResponse.ApiError
import com.tmdb.data.api.model.util.ApiResponse.Success
import com.tmdb.data.api.model.util.ApiResponse.UnknownError
import com.tmdb.data.api.model.util.NetworkErrorModel
import com.tmdb.data.model.mapping.movie.MovieApiToDataModelMapper
import com.tmdb.data.model.mapping.movie.MoviesApiToDataStateMapper
import com.tmdb.data.model.mapping.movie.movieApiToDataModelMapperImpl
import com.tmdb.data.model.mapping.movie.moviesApiToDataStateMapperImpl
import com.tmdb.data.model.movie.MovieDataModel
import com.tmdb.data.model.state.DataState
import com.tmdb.data.model.state.DataState.Error
import com.tmdb.data.model.util.ModelUtil
import kotlin.test.Test
import kotlin.test.assertEquals

class MoviesApiToDataStateMapperTest {
    private val baseUrl = "https://web.site.com"
    private val imageUrlProvider: ImageUrlProvider = ImageUrlProviderImpl(baseUrl)
    private val itemMapper: MovieApiToDataModelMapper = movieApiToDataModelMapperImpl(imageUrlProvider)
    private val listMapper: MoviesApiToDataStateMapper = moviesApiToDataStateMapperImpl(itemMapper)

    @Test
    fun `mapping Success ApiResponse_Success to DataState_Success`() {
        val input: ApiResponse<DataPage<Movie>, NetworkErrorModel> =
            Success(DataPage(page = 0, results = listOf(ModelUtil.movieModel)))

        val actual = listMapper.invoke(input)
        val expected = DataState.Success(listOf(ModelUtil.movieDataModel))
        assertEquals(expected, actual)
    }

    @Test
    fun `mapping Success ApiResponse_ApiError to DataState_Error`() {
        val input: ApiResponse<DataPage<Movie>, NetworkErrorModel> = ApiError()
        val actual = listMapper.invoke(input)
        val expected = Error<List<MovieDataModel>>(null)
        assertEquals(expected, actual)
    }

    @Test
    fun `mapping Success ApiResponse_NetworkError to DataState_NetworkError`() {
        val causeException = NetworkError()
        val input: ApiResponse<DataPage<Movie>, NetworkErrorModel> = ApiResponse.NetworkError(causeException)
        val actual = listMapper.invoke(input)
        val expected = DataState.NetworkError<List<MovieDataModel>>(causeException)
        assertEquals(expected, actual)
    }

    @Test
    fun `mapping Success ApiResponse_UnknownError to DataState_Error`() {
        val input: ApiResponse<DataPage<Movie>, NetworkErrorModel> = UnknownError()
        val actual = listMapper.invoke(input)
        val expected = Error<List<MovieDataModel>>(null)
        assertEquals(expected, actual)
    }
}
