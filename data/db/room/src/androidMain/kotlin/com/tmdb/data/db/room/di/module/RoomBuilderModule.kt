package com.tmdb.data.db.room.di.module

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tmdb.data.db.room.MovieDb
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun roomBuilderModule(): Module = module {
    factory<RoomDatabase.Builder<MovieDb>> {
        getDatabaseBuilder(get())
    }
}

private fun getDatabaseBuilder(context: Context): RoomDatabase.Builder<MovieDb> {
    val appContext = context.applicationContext
    val dbFile = appContext.getDatabasePath(MovieDb.DB_NAME)
    return Room.databaseBuilder<MovieDb>(
        context = appContext,
        name = dbFile.absolutePath,
    )
}
