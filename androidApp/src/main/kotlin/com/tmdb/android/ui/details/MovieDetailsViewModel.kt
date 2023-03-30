package com.tmdb.android.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tmdb.store.state.details.MovieDetailsFeatureState
import com.tmdb.shared.details.SharedMovieDetailsViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow


@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val sharedMovieDetailsViewModel: SharedMovieDetailsViewModel
) : ViewModel() {

    init {
        sharedMovieDetailsViewModel.init(viewModelScope)
    }

    val state: StateFlow<MovieDetailsFeatureState> = sharedMovieDetailsViewModel.state()

    override fun onCleared() {
        super.onCleared()
        sharedMovieDetailsViewModel.onClear()
    }
}