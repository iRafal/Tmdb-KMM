package com.tmdb.data.api.impl.di.module

import com.tmdb.data.api.config.url.provider.di.apiUrlProviderModule
import com.tmdb.data.api.impl.discover.DiscoverApi
import com.tmdb.data.api.impl.discover.DiscoverApiImpl
import com.tmdb.data.api.impl.genre.GenreApi
import com.tmdb.data.api.impl.genre.GenreApiImpl
import com.tmdb.data.api.impl.movie.MovieApi
import com.tmdb.data.api.impl.movie.MovieApiImpl
import com.tmdb.data.api.impl.person.PersonApi
import com.tmdb.data.api.impl.person.PersonApiImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

fun ktorApiModule() = module {
    includes(ktorApiUtilModule(), apiUrlProviderModule())
    singleOf(::DiscoverApiImpl) { bind<DiscoverApi>() }
    singleOf(::GenreApiImpl) { bind<GenreApi>() }
    singleOf(::MovieApiImpl) { bind<MovieApi>() }
    singleOf(::PersonApiImpl) { bind<PersonApi>() }
}
