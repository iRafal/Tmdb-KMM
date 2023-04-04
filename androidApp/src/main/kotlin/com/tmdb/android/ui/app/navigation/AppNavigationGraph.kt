package com.tmdb.android.ui.app.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.tmdb.shared_ui.SharedUiModule
import com.tmdb.shared_ui.app.navigation.AppNavigation.Close
import com.tmdb.shared_ui.app.navigation.AppNavigation.Home
import com.tmdb.shared_ui.app.navigation.AppNavigation.MovieDetails
import com.tmdb.shared_ui.details.MovieDetailsScreen
import com.tmdb.shared_ui.home.HomeScreen


fun NavGraphBuilder.appNavigationGraph(
    navController: NavController,
    onClose: () -> Unit,
) {
    composable(Home.route) {
        HomeScreen(
            onNavigate = { route ->
                navController.navigate(route)
            },
            homeViewModel = SharedUiModule.homeViewModel
        )
    }
    composable(MovieDetails.route) {
        val movieId = checkNotNull(it.arguments?.getString(MovieDetails.ARG_MOVIE_ID)?.toInt())
        MovieDetailsScreen(
            movieId,
            movieDetailsViewModel = SharedUiModule.movieDetailsViewModel,
            onNavigate = { route ->
                navController.navigate(route)
            }
        )
    }
    composable(Close.route) {
        onClose()
    }
}