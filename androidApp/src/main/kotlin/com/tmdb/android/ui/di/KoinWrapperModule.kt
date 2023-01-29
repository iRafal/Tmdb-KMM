package com.tmdb.android.ui.di

import com.tmdb.ui.shared.details.SharedMovieDetailsViewModel
import com.tmdb.ui.shared.di.SharedUiModuleDiProvider
import com.tmdb.ui.shared.home.SharedHomeViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object KoinWrapperModule {

    @Singleton
    @Provides
    fun sharedHomeViewModel(): SharedHomeViewModel = SharedUiModuleDiProvider().sharedHomeViewModel

    @Singleton
    @Provides
    fun sharedMovieDetailsViewModel(): SharedMovieDetailsViewModel =
        SharedUiModuleDiProvider().sharedMovieDetailsViewModel
}