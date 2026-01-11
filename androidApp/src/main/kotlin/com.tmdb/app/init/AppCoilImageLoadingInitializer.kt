package com.tmdb.app.init

import android.content.Context
import androidx.startup.Initializer
import coil3.ImageLoader
import coil3.SingletonImageLoader
import coil3.util.DebugLogger
import com.tmdb.BuildConfig

internal class AppCoilImageLoadingInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        SingletonImageLoader.setSafe {
            ImageLoader.Builder(context)
                .logger(if (BuildConfig.DEBUG) DebugLogger() else null)
                .build()
        }
    }

    override fun dependencies(): List<Class<out Initializer<*>?>?> = emptyList()
}
