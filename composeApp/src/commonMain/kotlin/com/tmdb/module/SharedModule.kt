package com.tmdb.module

import com.tmdb.details.MovieDetailsViewModel
import com.tmdb.home.HomeViewModel
import com.tmdb.home.TestViewModel
import com.tmdb.home.data.mapping.di.homeUiDataMappingModule
import com.tmdb.store.app.di.appStoreModule
import com.tmdb.util.dispatcher.di.DISPATCHER_IO
import com.tmdb.utils.permission.PermissionHandler
import com.tmdb.utils.permission.PermissionHandlerImpl
import com.tmdb.utils.permission.common.AppPermissionsController
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun sharedModule() = module {
    includes(appStoreModule(), homeUiDataMappingModule())
    factory {
        HomeViewModel(
            store = get(),
            homeFeatureToUiStateMapper = get(named("HomeFeatureToUiStateMapper")),
            homeMovieSectionToActionMapper = get(named("HomeMovieSectionToActionMapper")),
            dispatcherIo = get(named(DISPATCHER_IO)),
        )
    }
    factory { (movieId: String) -> MovieDetailsViewModel(movieId, get()) }
    factory { (permissionHandler: PermissionHandler) -> TestViewModel(permissionHandler, get()) }
    factory<PermissionHandler> { (permissionsController: AppPermissionsController) ->
        PermissionHandlerImpl(permissionsController)
    }
}
