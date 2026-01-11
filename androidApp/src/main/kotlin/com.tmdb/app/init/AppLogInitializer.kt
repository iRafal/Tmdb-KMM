package com.tmdb.app.init

import android.app.Application
import android.content.Context
import androidx.startup.Initializer
import com.tmdb.BuildConfig
import logcat.AndroidLogcatLogger

internal class AppLogInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        if (BuildConfig.DEBUG) AndroidLogcatLogger.installOnDebuggableApp(context as Application)
    }

    override fun dependencies(): List<Class<out Initializer<*>?>?> = emptyList()
}
