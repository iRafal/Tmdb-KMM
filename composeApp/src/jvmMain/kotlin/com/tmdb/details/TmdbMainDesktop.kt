package com.tmdb.details

import androidx.compose.ui.window.application
import com.tmdb.AppModule
import com.tmdb.MainWindow

fun main() {
    application {
        AppModule.start()
        MainWindow(this)
    }
}
