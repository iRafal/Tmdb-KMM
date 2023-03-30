package com.tmdb.android.ui.di

import com.tmdb.shared.details.SharedMovieDetailsViewModel
import com.tmdb.shared.di.SharedModuleDiProvider
import com.tmdb.shared.home.SharedHomeViewModel
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
    fun sharedHomeViewModel(): SharedHomeViewModel = SharedModuleDiProvider().sharedHomeViewModel

    @Singleton
    @Provides
    fun sharedMovieDetailsViewModel(): SharedMovieDetailsViewModel =
        SharedModuleDiProvider().sharedMovieDetailsViewModel
}