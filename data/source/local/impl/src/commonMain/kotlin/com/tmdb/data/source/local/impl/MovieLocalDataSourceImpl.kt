package com.tmdb.data.source.local.impl

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.tmdb.data.db.sqldelight.Movie
import com.tmdb.data.db.sqldelight.MovieDb
import com.tmdb.data.db.sqldelight.MovieQueries
import com.tmdb.data.model.movie.MovieDataModel
import com.tmdb.data.source.local.contract.MovieLocalDataSource
import com.tmdb.data.source.local.impl.mapping.MovieDataModelToEntityMapper
import com.tmdb.data.source.local.impl.mapping.MovieEntityToDataModelMapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieLocalDataSourceImpl(
    private val movieDb: MovieDb,
    private val movieEntityToDataModelMapper: MovieEntityToDataModelMapper,
    private val movieDataModelToEntityMapper: MovieDataModelToEntityMapper,
    private val unconfinedDispatcher: CoroutineDispatcher,
) : MovieLocalDataSource {

    private val movieQueries: MovieQueries
        get() = movieDb.movieQueries

    override suspend fun movie(movieId: Int): MovieDataModel? = movieQueries.getById(movieId.toLong())
        .executeAsOneOrNull()
        ?.let { movieEntityToDataModelMapper(it) }

    override suspend fun nowPlayingMovies(
        page: Int?,
        pageSize: Int?,
    ): List<MovieDataModel> {
        val limitAndOffset = processPageData(page, pageSize)
        val entities = if (limitAndOffset == null) {
            movieQueries.nowPlayingMovies().executeAsList()
        } else {
            movieQueries.nowPlayingMoviesByLimitAndOffset(
                limit = limitAndOffset.first.toLong(),
                offset = limitAndOffset.second.toLong(),
            ).executeAsList()
        }
        return entities.map { movieEntityToDataModelMapper(it) }
    }

    override suspend fun nowPopularMovies(
        page: Int?,
        pageSize: Int?,
    ): List<MovieDataModel> {
        val limitAndOffset = processPageData(page, pageSize)
        val entities = if (limitAndOffset == null) {
            movieQueries.nowPopularMovies().executeAsList()
        } else {
            movieQueries.nowPopularMoviesByLimitAndOffset(
                limit = limitAndOffset.first.toLong(),
                offset = limitAndOffset.second.toLong(),
            ).executeAsList()
        }
        return entities.map { movieEntityToDataModelMapper(it) }
    }

    override suspend fun topRatedMovies(
        page: Int?,
        pageSize: Int?,
    ): List<MovieDataModel> {
        val limitAndOffset = processPageData(page, pageSize)
        val entities = if (limitAndOffset == null) {
            movieQueries.topRatedMovies().executeAsList()
        } else {
            movieQueries.topRatedMoviesByLimitAndOffset(
                limit = limitAndOffset.first.toLong(),
                offset = limitAndOffset.second.toLong(),
            ).executeAsList()
        }
        return entities.map { movieEntityToDataModelMapper(it) }
    }

    override suspend fun upcomingMovies(
        page: Int?,
        pageSize: Int?,
    ): List<MovieDataModel> {
        val limitAndOffset = processPageData(page, pageSize)
        val entities = if (limitAndOffset == null) {
            movieQueries.upcomingMovies().executeAsList()
        } else {
            movieQueries.upcomingMoviesByLimitAndOffset(
                limit = limitAndOffset.first.toLong(),
                offset = limitAndOffset.second.toLong(),
            ).executeAsList()
        }
        return entities.map { movieEntityToDataModelMapper(it) }
    }

    private fun processPageData(
        page: Int?,
        pageSize: Int?,
    ): Pair<Int, Int>? {
        val _page = page ?: 0
        val _pageSize = pageSize ?: 0
        if (_page == 0 && _pageSize == 0) return null

        return if (page == 0) {
            _pageSize to 0
        } else {
            val currentPage = _page - 1
            val limit = currentPage * _pageSize
            val offset = _page * _pageSize
            limit to offset
        }
    }

    override suspend fun insert(movie: MovieDataModel) {
        movieQueries.insertOrReplaceObject(movieDataModelToEntityMapper(movie))
    }

    override suspend fun insertAll(movies: List<MovieDataModel>) {
        val mapped = movies.map { movieDataModelToEntityMapper(it) }
        movieQueries.transaction {
            mapped.forEach {
                movieQueries.insertOrReplaceObject(it)
            }
        }
    }

    override suspend fun insertByCategories(
        nowPlaying: List<MovieDataModel>,
        nowPopular: List<MovieDataModel>,
        topRatedMovies: List<MovieDataModel>,
        upcomingMovies: List<MovieDataModel>,
    ) {
        val mappedNowPlaying =
            nowPlaying.map { movieDataModelToEntityMapper(it).copy(now_playing = true) }
        val mappedNowPopular =
            nowPlaying.map { movieDataModelToEntityMapper(it).copy(now_popular = true) }
        val mappedTopRatedMovies =
            nowPlaying.map { movieDataModelToEntityMapper(it).copy(top_rated = true) }
        val mappedUpcomingMovies =
            nowPlaying.map { movieDataModelToEntityMapper(it).copy(upcoming = true) }

        val allItems =
            mappedNowPlaying + mappedNowPopular + mappedTopRatedMovies + mappedUpcomingMovies
        if (allItems.isEmpty()) return

        val mergedItemsList = mutableListOf<Movie>()
        val groupedByIdItems = allItems.groupBy { it.id }
        groupedByIdItems.forEach { (_, list) ->
            if (list.isNotEmpty()) {
                val mergedItem = list.first().copy(
                    now_playing = list.any { it.now_playing },
                    now_popular = list.any { it.now_popular },
                    top_rated = list.any { it.top_rated },
                    upcoming = list.any { it.upcoming },
                )
                mergedItemsList += mergedItem
            }
        }
        movieQueries.transaction {
            mergedItemsList.forEach {
                movieQueries.insertOrReplaceObject(it)
            }
        }
    }

    override suspend fun delete(movie: MovieDataModel) {
        val movieEntity = movieDataModelToEntityMapper(movie)
        movieQueries.delete(movieEntity.id)
    }

    override fun observeAll(): Flow<List<MovieDataModel>> = movieQueries.selectAll().asFlow().mapToList(
        unconfinedDispatcher,
    ).map {
        it.map(movieEntityToDataModelMapper)
    }

    override suspend fun getAll(): List<MovieDataModel> = movieQueries.selectAll().executeAsList().map {
        movieEntityToDataModelMapper(it)
    }

    override suspend fun getById(id: Int): MovieDataModel? = movieQueries.getById(
        id.toLong(),
    ).executeAsOneOrNull()?.let {
        movieEntityToDataModelMapper(it)
    }
}
