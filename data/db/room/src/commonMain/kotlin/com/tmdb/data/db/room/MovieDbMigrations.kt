package com.tmdb.data.db.room

import androidx.room.migration.Migration
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.execSQL
import com.tmdb.data.db.room.movie.MovieEntity

object MovieDbMigrations {
    val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(connection: SQLiteConnection) {
            super.migrate(connection)
            val movieTable = MovieEntity.MOVIE_TABLE_NAME
            connection.execSQL(
                "ALTER TABLE `$movieTable` ADD COLUMN `${MovieEntity.MOVIE_TABLE_COLUMN_NOW_PLAYING}` INTEGER NOT NULL DEFAULT 0;",
            )
            connection.execSQL(
                "ALTER TABLE `$movieTable` ADD COLUMN `${MovieEntity.MOVIE_TABLE_COLUMN_NOW_POPULAR}` INTEGER NOT NULL DEFAULT 0;",
            )
            connection.execSQL(
                "ALTER TABLE `$movieTable` ADD COLUMN `${MovieEntity.MOVIE_TABLE_COLUMN_TOP_RATED}` INTEGER NOT NULL DEFAULT 0;",
            )
            connection.execSQL(
                "ALTER TABLE `$movieTable` ADD COLUMN `${MovieEntity.MOVIE_TABLE_COLUMN_UPCOMING}` INTEGER NOT NULL DEFAULT 0;",
            )
        }
    }
}
