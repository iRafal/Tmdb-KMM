package com.tmdb.data.db.room

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase.Builder
import com.tmdb.data.db.room.MovieDbMigrations.MIGRATION_1_2

object RoomDbAndroid {
    fun getInMemoryDbBuilder(context: Context): Builder<MovieDb> =
        Room.inMemoryDatabaseBuilder<MovieDb>(context).addMigrations(MIGRATION_1_2)

    fun getInMemoryDb(context: Context): MovieDb = getInMemoryDbBuilder(context).build()
}
