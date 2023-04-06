package com.tmdb.shared.details

import com.tmdb.shared.core.viewModel.BaseViewModel
import com.tmdb.store.state.details.MovieDetailsFeatureState
import kotlinx.coroutines.flow.StateFlow


class MovieDetailsViewModel(
    private val sharedMovieDetailsViewModel: SharedMovieDetailsViewModel
) : BaseViewModel() {

    init {
        sharedMovieDetailsViewModel.init(coroutineScope)
    }

    val state: StateFlow<MovieDetailsFeatureState> = sharedMovieDetailsViewModel.state()

    override fun onCleared() {
        super.onCleared()
        sharedMovieDetailsViewModel.onClear()
    }
}