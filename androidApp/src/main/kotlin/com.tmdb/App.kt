package com.tmdb

import android.app.Application
import androidx.work.Configuration
import androidx.work.WorkerFactory
import org.koin.android.ext.android.inject

class App : Application(), Configuration.Provider {
    private val workerFactory: WorkerFactory by inject()

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()
}
