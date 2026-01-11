package com.tmdb.app

import androidx.compose.runtime.Composable
import com.tmdb.app.navigation.Navigation
import com.tmdb.core.theme.Tmdb_TestTheme

@Composable
fun ComposeApp() {
    Tmdb_TestTheme {
        Navigation()
    }
}
