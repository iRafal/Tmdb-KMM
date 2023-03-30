package com.tmdb.data.source.local.impl.di

import com.tmdb.data.source.local.impl.mapping.MovieDataModelToEntityMapper
import com.tmdb.data.source.local.impl.mapping.MovieEntityToDataModelMapper
import com.tmdb.data.source.local.impl.mapping.movieDataModelToEntityMapperImpl
import com.tmdb.data.source.local.impl.mapping.movieEntityToDataModelMapperImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val MOVIE_ENTITY_TO_DATA_MODEL_MAPPER = "movie_entity_to_data_model_mapper"
const val MOVIE_DATA_MODEL_TO_ENTITY_MAPPER = "movie_data_model_to_entity_mapper"

fun localDataSourceDataMappingModule() = module {
    factory<MovieEntityToDataModelMapper>(named(MOVIE_ENTITY_TO_DATA_MODEL_MAPPER)) { ::movieEntityToDataModelMapperImpl }
    factory<MovieDataModelToEntityMapper>(named(MOVIE_DATA_MODEL_TO_ENTITY_MAPPER)) { ::movieDataModelToEntityMapperImpl }
}

