package com.tmdb.data.model.di

import com.tmdb.data.api.config.di.module.imageUrlModule
import com.tmdb.data.model.mapping.movie.MovieApiToDataModelMapper
import com.tmdb.data.model.mapping.movie.MoviesApiToDataStateMapper
import com.tmdb.data.model.mapping.movie.movieApiToDataModelMapperImpl
import com.tmdb.data.model.mapping.movie.moviesApiToDataStateMapperImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module

val movieApiMappingModule = module {
    includes(imageUrlModule)
    single<MovieApiToDataModelMapper>(named("MovieApiToDataModelMapper")) {
        movieApiToDataModelMapperImpl(get())
    }
    single<MoviesApiToDataStateMapper>(
        named("MoviesApiToDataStateMapper")
    ) {
        moviesApiToDataStateMapperImpl(
            movieApiToDataModelMapper = get(named("MovieApiToDataModelMapper"))
        )
    }
}