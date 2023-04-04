package com.tmdb.shared_ui.details

import com.tmdb.store.state.details.MovieDetailsFeatureState
import com.tmdb.shared.details.SharedMovieDetailsViewModel
import com.tmdb.shared_ui.core.viewModel.BaseViewModel
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