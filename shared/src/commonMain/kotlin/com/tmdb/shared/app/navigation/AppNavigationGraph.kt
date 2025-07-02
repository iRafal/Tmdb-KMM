package com.tmdb.shared.app.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.tmdb.shared.details.MovieDetailsScreen
import com.tmdb.shared.home.HomeScreen

fun NavGraphBuilder.appNavigationGraph(navController: NavController) {
    composable(AppNavigation.Home.route) {
        HomeScreen(navController = navController)
    }
    composable(
        route = AppNavigation.MovieDetails.route,
        arguments = AppNavigation.MovieDetails.arguments,
    ) { backStackEntry ->
        val movieId = checkNotNull(backStackEntry.savedStateHandle.get<String>(ARG_MOVIE_ID)?.toInt())
        MovieDetailsScreen(navController, movieId)
    }
}
