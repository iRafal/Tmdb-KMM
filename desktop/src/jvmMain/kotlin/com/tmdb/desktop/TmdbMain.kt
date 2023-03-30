package com.tmdb.desktop

import androidx.compose.ui.window.application
import com.tmdb.desktop.ui.MainWindow
import com.tmdb.shared.SharedModule

fun main() {
    application {
        SharedModule.start()
        MainWindow(this)
    }
}