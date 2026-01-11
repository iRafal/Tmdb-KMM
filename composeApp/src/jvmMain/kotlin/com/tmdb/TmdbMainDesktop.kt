package com.tmdb

import androidx.compose.ui.window.application

fun main() {
    application {
        AppModule.start()
        MainWindow(this)
    }
}
