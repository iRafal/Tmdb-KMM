package com.tmdb.data.api.config.url.provider.di

import com.tmdb.data.api.config.url.provider.base.di.baseUrlProviderModule
import com.tmdb.data.api.config.url.provider.base.BaseUrlProvider
import com.tmdb.data.api.config.url.provider.discover.DiscoverUrlProvider
import com.tmdb.data.api.config.url.provider.discover.DiscoverUrlProviderImpl
import com.tmdb.data.api.config.url.provider.genre.GenreUrlProvider
import com.tmdb.data.api.config.url.provider.genre.GenreUrlProviderImpl
import com.tmdb.data.api.config.url.provider.movie.MovieUrlProvider
import com.tmdb.data.api.config.url.provider.movie.MovieUrlProviderImpl
import com.tmdb.data.api.config.url.provider.person.PersonUrlProviderImpl
import com.tmdb_test.api.config.url.provider.person.PersonUrlProvider
import org.koin.dsl.module


fun apiUrlProviderModule() = module {
    includes(baseUrlProviderModule())

    single<DiscoverUrlProvider>() {
        val baseUrlProvider: BaseUrlProvider = get()
        DiscoverUrlProviderImpl(baseUrlProvider.discoverApiUrl)
    }

    single<GenreUrlProvider>() {
        val baseUrlProvider: BaseUrlProvider = get()
        GenreUrlProviderImpl(baseUrlProvider.genreApiUrl)
    }

    single<MovieUrlProvider>() {
        val baseUrlProvider: BaseUrlProvider = get()
        MovieUrlProviderImpl(baseUrlProvider.movieApiUrl)
    }

    single<PersonUrlProvider>() {
        val baseUrlProvider: BaseUrlProvider = get()
        PersonUrlProviderImpl(baseUrlProvider.personApiUrl)
    }
}