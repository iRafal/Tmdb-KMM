package com.tmdb

import android.app.Application
import android.os.StrictMode
import android.os.StrictMode.VmPolicy
import androidx.work.Configuration
import androidx.work.WorkerFactory
import coil3.ImageLoader
import coil3.SingletonImageLoader
import coil3.util.DebugLogger
import com.tmdb.shared.AppModule
import com.tmdb.util.logging.AndroidReleaseLogcatLogger
import logcat.AndroidLogcatLogger
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.workmanager.koin.workManagerFactory
import org.koin.core.logger.Level

class App : Application(), Configuration.Provider {
    private val workerFactory: WorkerFactory by inject()

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()

    override fun onCreate() {
        super.onCreate()
        initLogging()
        initCoil()
        initDi()
        //initIoStrictPolicy()
    }

    private fun initLogging() {
        if (BuildConfig.DEBUG) {
            AndroidLogcatLogger.installOnDebuggableApp(this)
        } else {
            AndroidReleaseLogcatLogger.installOnReleaseApp(
                this,
                onErrorLog = { priority, tag, message -> /* TODO pass logs to crash tracking tool */ },
            )
        }
    }

    private fun initCoil() {
        SingletonImageLoader.setSafe {
            ImageLoader.Builder(this)
                .logger(if (BuildConfig.DEBUG) DebugLogger() else null)
                .build()
        }
    }

    private fun initDi() {
        AppModule.start {
            androidLogger(if (BuildConfig.DEBUG) Level.DEBUG else Level.INFO)
            androidContext(this@App)
            workManagerFactory()
        }
    }

    private fun initIoStrictPolicy() {
        StrictMode.setThreadPolicy(
            StrictMode.ThreadPolicy.Builder()
                .detectDiskReads()
                .detectDiskWrites()
                .detectAll()
                .penaltyLog()
                .build(),
        )
        StrictMode.setVmPolicy(
            VmPolicy.Builder()
                .detectLeakedSqlLiteObjects()
                .detectLeakedClosableObjects()
                .penaltyLog()
                .build(),
        )
    }
}
