package com.tmdb.desktop

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.tmdb.shared.SharedModule

fun main() {
    application {
        SharedModule.start()

        val windowState = rememberWindowState(width = 300.dp, height = 300.dp)
        Window(
            onCloseRequest = ::exitApplication,
            title = "Tmdb Desktop",
            state = windowState
        ) {
            MaterialTheme {
                Text("Tmdb Desktop Text", fontSize = 25.sp)
            }
        }
    }
}