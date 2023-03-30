package com.tmdb.desktop.ui.app.navigation

import androidx.compose.runtime.Composable
import com.tmdb.desktop.ui.home.HomeScreen
import com.tmdb.shared.SharedModule
import com.tmdb.shared_ui.core.theme.Tmdb_TestTheme
import kotlinx.coroutines.MainScope

@Composable
fun AppNavigation() {
    val viewModel = SharedModule.sharedHomeViewModel
    viewModel.init(MainScope())

    Tmdb_TestTheme {
        HomeScreen(viewModel)
    }
}