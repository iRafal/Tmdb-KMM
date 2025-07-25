package com.tmdb.shared.app

import androidx.compose.runtime.Composable
import com.tmdb.shared.core.theme.Tmdb_TestTheme
import com.tmdb.shared.app.navigation.Navigation

@Composable
fun ComposeApp() {
    Tmdb_TestTheme {
        Navigation()
    }
}
