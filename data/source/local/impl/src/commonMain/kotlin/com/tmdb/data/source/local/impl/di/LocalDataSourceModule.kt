package com.tmdb.data.source.local.impl.di

import com.tmdb.data.db.sqldelight.di.module.sqlDelightModule
import com.tmdb.data.source.local.contract.MovieLocalDataSource
import com.tmdb.data.source.local.impl.MovieLocalDataSourceImpl
import com.tmdb.util.dispatcher.di.DISPATCHER_UNCONFINED
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun localDataSourceModule() = module {
    includes(localDataSourceDataMappingModule(), sqlDelightModule())
    single<MovieLocalDataSource> {
        MovieLocalDataSourceImpl(
            movieDb = get(),
            movieEntityToDataModelMapper = get(named(MOVIE_ENTITY_TO_DATA_MODEL_MAPPER)),
            movieDataModelToEntityMapper = get(named(MOVIE_DATA_MODEL_TO_ENTITY_MAPPER)),
            get(named(DISPATCHER_UNCONFINED)),
        )
    }
}
