package com.tmdb

import androidx.compose.ui.window.ComposeUIViewController
import com.tmdb.shared.app.ComposeApp

fun MainViewController(
    onDarkTheme: (isDarkTheme: Boolean?) -> Unit,
) = ComposeUIViewController {
    ComposeApp()
}
