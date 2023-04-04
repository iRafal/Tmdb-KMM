package com.tmdb.desktop

import androidx.compose.ui.window.application
import com.tmdb.desktop.ui.MainWindow
import com.tmdb.shared_ui.SharedUiModule

fun main() {
    application {
        SharedUiModule.start()
        MainWindow(this)
    }
}