package com.tmdb.data.db.room.di.module

import androidx.room.Room
import androidx.room.RoomDatabase
import com.tmdb.data.db.room.MovieDb
import org.koin.core.module.Module
import org.koin.dsl.module
import java.io.File

actual fun roomBuilderModule(): Module = module {
    factory<RoomDatabase.Builder<MovieDb>> { getDatabaseBuilder() }
}

private fun getDatabaseBuilder(): RoomDatabase.Builder<MovieDb> {
    val dbFile = File(System.getProperty("java.io.tmpdir"), MovieDb.DB_NAME)
    return Room.databaseBuilder<MovieDb>(name = dbFile.absolutePath)
}
