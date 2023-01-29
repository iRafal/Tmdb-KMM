package com.tmdb.android.ui.app.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.tmdb.android.ui.details.MovieDetailsScreen
import com.tmdb.android.ui.home.HomeScreen
import com.tmdb.android.ui.app.navigation.AppNavigation.Close
import com.tmdb.android.ui.app.navigation.AppNavigation.Home
import com.tmdb.android.ui.app.navigation.AppNavigation.MovieDetails


fun NavGraphBuilder.appNavigationGraph(
    navController: NavController,
    onClose: () -> Unit,
) {
    composable(Home.route) {
        HomeScreen(navController)
    }
    composable(MovieDetails.route) {
        val movieId = checkNotNull(it.arguments?.getString(MovieDetails.ARG_MOVIE_ID)?.toInt())
        MovieDetailsScreen(navController, movieId)
    }
    composable(Close.route) {
        onClose()
    }
}