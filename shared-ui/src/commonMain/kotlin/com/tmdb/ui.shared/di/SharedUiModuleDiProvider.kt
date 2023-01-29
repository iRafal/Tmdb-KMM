package com.tmdb.ui.shared.di

import com.tmdb.ui.shared.details.SharedMovieDetailsViewModel
import com.tmdb.ui.shared.home.SharedHomeViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SharedUiModuleDiProvider : KoinComponent {
    private val _sharedHomeViewModel: SharedHomeViewModel by inject()
    val sharedHomeViewModel: SharedHomeViewModel = _sharedHomeViewModel

    private val _sharedMovieDetailsViewModel: SharedMovieDetailsViewModel by inject()
    val sharedMovieDetailsViewModel = _sharedMovieDetailsViewModel
}