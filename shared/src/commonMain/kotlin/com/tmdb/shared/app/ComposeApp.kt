package com.tmdb.shared.app

import androidx.compose.runtime.Composable
import com.tmdb.shared.app.navigation.Navigation

@Composable
fun ComposeApp(onClose: () -> Unit) {
    Navigation(onClose = onClose)
}
