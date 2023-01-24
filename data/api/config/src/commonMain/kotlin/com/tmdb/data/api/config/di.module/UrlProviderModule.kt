package com.tmdb.data.api.config.di.module

import com.tmdb.data.api.config.url.provider.BaseUrlProvider
import com.tmdb.data.api.config.url.provider.BaseUrlProviderImpl
import com.tmdb.data.api.config.url.provider.discover.DiscoverUrlProvider
import com.tmdb.data.api.config.url.provider.discover.DiscoverUrlProviderImpl
import com.tmdb.data.api.config.url.provider.genre.GenreUrlProvider
import com.tmdb.data.api.config.url.provider.genre.GenreUrlProviderImpl
import com.tmdb.data.api.config.url.provider.movie.MovieUrlProvider
import com.tmdb.data.api.config.url.provider.movie.MovieUrlProviderImpl
import com.tmdb.data.api.config.url.provider.person.PersonUrlProviderImpl
import com.tmdb_test.api.config.url.provider.person.PersonUrlProvider
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


val baseUrlModule = module() {
    singleOf(::BaseUrlProviderImpl) { bind<BaseUrlProvider>() }
}

val apiUrlProviderModule = module() {
    if (!baseUrlModule.isLoaded) {
        includes(baseUrlModule)
    }
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