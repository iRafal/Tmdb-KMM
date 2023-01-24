package com.tmdb.android

import android.app.Application
import android.os.StrictMode
import android.os.StrictMode.VmPolicy
import com.tmdb.di.DiKoinController

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        initIoStrictPolicy()
        DiKoinController.initKoin()
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
//                .penaltyDeath()
                .build()
        )
    }
}