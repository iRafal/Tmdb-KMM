package com.tmdb

import androidx.compose.ui.window.ComposeUIViewController
import com.tmdb.app.ComposeApp
import platform.UIKit.UIWindow

fun MainViewController(
    window: UIWindow? = null
) = ComposeUIViewController {
    ComposeApp()
}
