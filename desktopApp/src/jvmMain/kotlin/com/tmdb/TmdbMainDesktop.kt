package com.tmdb

import androidx.compose.ui.window.application
import com.tmdb.shared.AppModule

fun main() {
    application {
        AppModule.start()
        MainWindow(this)
    }
}
