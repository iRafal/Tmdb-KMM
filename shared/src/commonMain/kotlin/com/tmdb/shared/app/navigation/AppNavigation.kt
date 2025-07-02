package com.tmdb.shared.app.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

internal const val ARG_MOVIE_ID = "movieId"

sealed class AppNavigation(val route: String, val arguments: List<NamedNavArgument> = emptyList()) {
    data object Home : AppNavigation("home")

    data object MovieDetails : AppNavigation(
        route = "movie/details?movieId={movieId}",
        arguments = listOf(navArgument(ARG_MOVIE_ID) { type = NavType.StringType }),
    ) {
        fun getRouteNameWithArguments(movieId: String): String = "movie/details?movieId=$movieId"
    }

    data object Close : AppNavigation("close")
}
