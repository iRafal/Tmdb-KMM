package com.tmdb.data.db.dataStore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import java.io.File

/**
 * https://developer.android.com/kotlin/multiplatform/datastore#jvm-(desktop)
 * System.getProperty("java.io.tmpdir") points to the temporary folder on the system,
 * which might be cleaned upon reboot.
 * On macOS, you can instead use the ~/Library/Application Support/[your-app] folder.
 */
private const val BASE_DIR = "java.io.tmpdir"

fun createDataStore(): DataStore<Preferences> = createDataStore(
    producePath = {
        val file = File(System.getProperty(BASE_DIR), DATA_STORE_FILE_NAME)
        file.absolutePath
    },
)
