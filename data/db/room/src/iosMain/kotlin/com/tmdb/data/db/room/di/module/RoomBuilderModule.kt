package com.tmdb.data.db.room.di.module

import androidx.room.Room
import androidx.room.RoomDatabase
import com.tmdb.data.db.room.MovieDb
import kotlinx.cinterop.ExperimentalForeignApi
import org.koin.core.module.Module
import org.koin.dsl.module
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

actual fun roomBuilderModule(): Module = module {
    factory<RoomDatabase.Builder<MovieDb>> { getDatabaseBuilder() }
}

private fun getDatabaseBuilder(): RoomDatabase.Builder<MovieDb> {
    val dbFilePath = "${documentDirectory()}/${MovieDb.DB_NAME}"
    return Room.databaseBuilder<MovieDb>(name = dbFilePath)
}

@OptIn(ExperimentalForeignApi::class)
private fun documentDirectory(): String {
    val documentDirectory = NSFileManager.defaultManager.URLForDirectory(
        directory = NSDocumentDirectory,
        inDomain = NSUserDomainMask,
        appropriateForURL = null,
        create = false,
        error = null,
    )
    return requireNotNull(documentDirectory?.path)
}
