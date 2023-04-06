package com.tmdb.android.ui.di

import com.tmdb.shared_ui.SharedUiModule
import com.tmdb.shared.details.MovieDetailsViewModel
import com.tmdb.shared.home.HomeViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object KoinWrapperModule {
    @Provides
    fun homeViewModel(): HomeViewModel = SharedUiModule.homeViewModel

    @Provides
    fun movieDetailsViewModel(): MovieDetailsViewModel = SharedUiModule.movieDetailsViewModel
}