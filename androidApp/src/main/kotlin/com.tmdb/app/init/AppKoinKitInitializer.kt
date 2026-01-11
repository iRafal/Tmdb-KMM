package com.tmdb.app.init

import android.content.Context
import androidx.startup.Initializer
import com.tmdb.App
import com.tmdb.AppModule
import com.tmdb.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.workmanager.koin.workManagerFactory
import org.koin.core.logger.Level

internal class AppKoinKitInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        AppModule.start {
            androidLogger(if (BuildConfig.DEBUG) Level.DEBUG else Level.INFO)
            androidContext(context as App)
            workManagerFactory()
        }
    }

    override fun dependencies(): List<Class<out Initializer<*>?>?> = emptyList()
}
