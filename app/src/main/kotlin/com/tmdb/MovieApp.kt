package com.tmdb

import android.app.Application
import android.os.StrictMode
import android.os.StrictMode.VmPolicy
import android.util.Log
import androidx.work.Configuration
import coil3.ImageLoader
import coil3.SingletonImageLoader
import com.tmdb.di.module.appModule
import com.tmdb.ui.core.util.logging.android.AndroidLogging
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.workmanager.factory.KoinWorkerFactory
import org.koin.androidx.workmanager.koin.workManagerFactory
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named

class MovieApp : Application(), Configuration.Provider, KoinComponent {
    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setMinimumLoggingLevel(if (BuildConfig.DEBUG) Log.DEBUG else Log.ERROR)
            .setWorkerFactory(KoinWorkerFactory())
            .build()

    private val coilImageLoader : ImageLoader by inject(named("CoilOkHttpImageLoader"))

    override fun onCreate() {
        super.onCreate()
        initDi()
        AndroidLogging.init(this)
//        initIoStrictPolicy()
        initCoil()
    }

    private fun initDi() {
        startKoin {
            androidContext(this@MovieApp)
            modules(appModule())
            workManagerFactory()
        }
    }

    private fun initCoil() {
        SingletonImageLoader.setSafe { coilImageLoader }
    }

    private fun initIoStrictPolicy() {
        StrictMode.setThreadPolicy(
            StrictMode.ThreadPolicy.Builder()
                .detectDiskReads()
                .detectDiskWrites()
                .detectAll()
                .penaltyLog()
                .build()
        )
        StrictMode.setVmPolicy(
            VmPolicy.Builder()
                .detectLeakedSqlLiteObjects()
                .detectLeakedClosableObjects()
                .penaltyLog()
                .build()
        )
    }
}
