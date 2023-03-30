@file:OptIn(ExperimentalCoroutinesApi::class)

package com.tmdb.data.db.sqldelight

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.db.SqlDriver
import com.tmdb.data.db.sqldelight.di.DISPATCHER_TEST_STANDARD
import com.tmdb.data.db.sqldelight.di.koinTestModule
import com.tmdb.data.db.sqldelight.di.module.sqlDelightModule
import com.tmdb.data.db.sqldelight.di.module.sqlDelightJvmDbDriverModule
import com.tmdb.data.db.sqldelight.util.ModelUtil
import java.io.IOException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.qualifier.named
import org.koin.test.KoinTest
import org.koin.test.get

class MovieEntityTest : KoinTest {

    private val dispatcher: CoroutineDispatcher
        get() = get(named(DISPATCHER_TEST_STANDARD))

    private val movieEntity = ModelUtil.movieEntity
    private val movieId = ModelUtil.movieEntity.id

    private val movieDb: MovieDb
        get() = get()

    private val movieQueries: MovieQueries
        get() = movieDb.movieQueries

    @Before
    fun setup() {
        startKoin {
            modules(koinTestModule, sqlDelightJvmDbDriverModule(), sqlDelightModule())
        }
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() = runTest {
        get<SqlDriver>().close()
        Dispatchers.resetMain()
        stopKoin()
    }

    @Test
    @Throws(IOException::class)
    fun write_GetMovieById() = runTest {
        movieQueries.insertObject(movieEntity)
        movieQueries.getById(movieId).executeAsOne().run {
            assertEquals(movieEntity, this)
        }
    }

    @Test
    @Throws(IOException::class)
    fun addNothing_GetMovieById() = runTest {
        getById().run { assertNull(this) }
    }

    @Test
    @Throws(IOException::class)
    fun write_GetAllMovieEntities() = runTest {
        movieQueries.insertObject(movieEntity)
        movieQueries.selectAll().executeAsList().run { assertTrue(this.contains(movieEntity)) }
    }

    @Test
    @Throws(IOException::class)
    fun addNothing_GetAllMovies() = runTest {
        movieQueries.selectAll().executeAsList().run { assertEquals(this, emptyList<Movie>()) }
    }

    @Test
    @Throws(IOException::class)
    fun write_ObserveAllMovies() = runTest {
        movieQueries.insertObject(movieEntity)
        val allMovies =
            movieQueries.selectAll().asFlow().mapToList(dispatcher).take(1).firstOrNull()
        assertTrue(allMovies?.contains(movieEntity) == true)
    }

    @Test
    @Throws(IOException::class)
    fun addNothing_ObserveAllMovies() = runTest {
        val allMovies =
            movieQueries.selectAll().asFlow().mapToList(dispatcher).take(1).firstOrNull()
        advanceUntilIdle()
        assertEquals(allMovies, emptyList<Movie>())
    }

    @Test
    @Throws(IOException::class)
    fun write_DeleteMovieById() = runTest {
        movieQueries.insertObject(movieEntity)
        getById().run { assertEquals(movieEntity, this) }
        movieQueries.delete(movieId)
        getById().run { assertNull(this) }
    }

    @Test
    @Throws(IOException::class)
    fun addNothing_DeleteMovieById() = runTest {
        getById().run { assertNull(this) }
        movieQueries.delete(movieId)
        getById().run { assertNull(this) }
    }

    @Test
    @Throws(IOException::class)
    fun write_DeleteAllMovies() = runTest {
        movieQueries.insertObject(movieEntity)
        getById().run { assertEquals(movieEntity, this) }
        movieQueries.deleteAll()
        getById().also { deletedMovie -> assertNull(deletedMovie) }
    }

    @Test
    @Throws(IOException::class)
    fun addNothing_DeleteAllMovies() = runTest {
        getById().run { assertNull(this) }
        movieQueries.deleteAll()
        getById().run { assertNull(this) }
    }

    private fun getById(): Movie? = movieQueries.getById(movieId).executeAsOneOrNull()
}