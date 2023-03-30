package com.tmdb.shared.di

import com.tmdb.shared.details.SharedMovieDetailsViewModel
import com.tmdb.shared.home.SharedHomeViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SharedModuleDiProvider : KoinComponent {
    private val _sharedHomeViewModel: SharedHomeViewModel by inject()
    val sharedHomeViewModel: SharedHomeViewModel = _sharedHomeViewModel

    private val _sharedMovieDetailsViewModel: SharedMovieDetailsViewModel by inject()
    val sharedMovieDetailsViewModel = _sharedMovieDetailsViewModel
}