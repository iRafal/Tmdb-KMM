package com.tmdb.shared.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import com.tmdb.shared.app.navigation.AppNavigation
import com.tmdb.shared.home.data.HomeUiData
import com.tmdb.shared.utils.permission.PermissionHandlerImpl
import com.tmdb.shared.utils.permission.common.AppPermissionsController
import com.tmdb.shared.utils.permission.rememberPermissionsControllerWithBind
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = koinViewModel<HomeViewModel>(),
    navController: NavController,
) {
    val permissionsController: AppPermissionsController = rememberPermissionsControllerWithBind()
    val permissionHandler = remember(permissionsController) {
        PermissionHandlerImpl(permissionsController)
    }
    val testViewModel: TestViewModel = koinViewModel<TestViewModel> {
        parametersOf(permissionHandler)
    }

    LaunchedEffect(Unit) {
        testViewModel.requestPermissions()
    }

    val data by homeViewModel.uiStateFlow.collectAsState(HomeUiData.INITIAL)

    val onEvent: (HomeUiEvent) -> Unit = { event ->
        when (event) {
            HomeUiEvent.NavigateBack -> navController.popBackStack()
            is HomeUiEvent.OpenMovie -> {
                navController.navigate(AppNavigation.MovieDetails.getRouteNameWithArguments(event.id.toString()))
            }

            is HomeUiEvent.ReloadMovieSection -> homeViewModel.onReloadMovieSection(event.movieSection)
        }
    }

    HomeScreenUi(data, onEvent)
}
