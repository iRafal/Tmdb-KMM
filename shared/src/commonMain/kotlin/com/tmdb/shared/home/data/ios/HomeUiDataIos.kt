package com.tmdb.shared.home.data.ios

import com.tmdb.shared.home.data.HomeMovieSection
import com.tmdb.shared.home.data.HomeUiData.Movie

data class HomeUiDataIos(
    val isError: Boolean,
    val isLoading: Boolean,
    val isNetworkError: Boolean,
    val isSuccess: Boolean,
    val section: HomeMovieSection,
    val data: List<Movie> = emptyList(),
) {
    companion object {
        fun error(section: HomeMovieSection) = HomeUiDataIos(
            isError = true,
            isLoading = false,
            isNetworkError = false,
            isSuccess = false,
            section = section
        )

        fun networkError(section: HomeMovieSection) = HomeUiDataIos(
            isError = false,
            isLoading = false,
            isNetworkError = true,
            isSuccess = false,
            section = section
        )

        fun loading(section: HomeMovieSection) = HomeUiDataIos(
            isError = false,
            isLoading = true,
            isNetworkError = false,
            isSuccess = false,
            section = section
        )

        fun success(section: HomeMovieSection, data: List<Movie>) = HomeUiDataIos(
            isError = false,
            isLoading = false,
            isNetworkError = false,
            isSuccess = true,
            section = section,
            data = data
        )
    }
}
