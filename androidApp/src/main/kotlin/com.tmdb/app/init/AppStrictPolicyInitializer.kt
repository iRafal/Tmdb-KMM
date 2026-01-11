package com.tmdb.app.init

import android.content.Context
import android.os.StrictMode
import android.os.StrictMode.VmPolicy
import androidx.startup.Initializer
import com.tmdb.BuildConfig

internal class AppStrictPolicyInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        // initIoStrictPolicy() //INFO: commented, spams in logs too much
    }

    private fun initIoStrictPolicy() {
        if (!BuildConfig.DEBUG) return

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

    override fun dependencies(): List<Class<out Initializer<*>?>?> = emptyList()
}
