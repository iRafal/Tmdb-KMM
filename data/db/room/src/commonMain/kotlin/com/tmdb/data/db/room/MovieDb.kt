package com.tmdb.data.db.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.tmdb.data.db.room.MovieDbMigrations.MIGRATION_1_2
import com.tmdb.data.db.room.converters.LocalDateConverter
import com.tmdb.data.db.room.converters.LocalDateTimeConverter
import com.tmdb.data.db.room.movie.MovieDao
import com.tmdb.data.db.room.movie.MovieEntity
import kotlin.coroutines.CoroutineContext

@Database(
    entities = [MovieEntity::class],
    version = 2,
    exportSchema = true,
)
@TypeConverters(
    LocalDateTimeConverter::class,
    LocalDateConverter::class,
)
abstract class MovieDb : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object {
        internal const val DB_NAME = "Movie.db"

        internal fun getRoomDatabase(
            builder: Builder<MovieDb>,
            queryCoroutineContext: CoroutineContext,
        ): MovieDb = builder
            .addMigrations(MIGRATION_1_2)
            .fallbackToDestructiveMigrationOnDowngrade(dropAllTables = true)
            .setDriver(BundledSQLiteDriver())
            .setQueryCoroutineContext(queryCoroutineContext)
            .build()
    }
}
