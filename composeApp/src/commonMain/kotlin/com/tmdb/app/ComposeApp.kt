package com.tmdb.app

import androidx.compose.runtime.Composable
import com.tmdb.core.theme.Tmdb_TestTheme
import com.tmdb.app.navigation.Navigation

@Composable
fun ComposeApp() {
    Tmdb_TestTheme {
        Navigation()
    }
}
