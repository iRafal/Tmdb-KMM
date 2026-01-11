package com.tmdb

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.ApplicationScope
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.rememberWindowState
import com.tmdb.app.ComposeApp

@Composable
fun MainWindow(applicationScope: ApplicationScope) {
    val windowState = rememberWindowState()
    Window(
        onCloseRequest = { applicationScope.exitApplication() },
        title = "Tmdb Desktop",
        state = windowState
    ) {
        ComposeApp()
    }
}
