package com.tmdb.shared.utils.permission.common

expect class AppPermissionsController {
    suspend fun getPermissionState(permission: Permission): PermissionState
    suspend fun providePermission(permission: Permission)
    fun openAppSettings()
}
