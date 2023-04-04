package com.tmdb.desktop.ui.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import com.tmdb.shared_ui.SharedUiModule
import com.tmdb.shared_ui.core.theme.Tmdb_TestTheme
import com.tmdb.shared_ui.home.HomeScreen

@Composable
fun AppNavigation() {
    val homeViewModel = remember { SharedUiModule.homeViewModel }

    Tmdb_TestTheme {
        HomeScreen(
            homeViewModel,
            onNavigate = {
            }
        )
    }

    DisposableEffect(Unit) {
        onDispose {
            homeViewModel.dispose()
        }
    }
}