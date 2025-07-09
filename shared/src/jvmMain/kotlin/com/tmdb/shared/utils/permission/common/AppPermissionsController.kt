package com.tmdb.shared.utils.permission.common

actual class AppPermissionsController {
    actual suspend fun getPermissionState(permission: Permission): PermissionState = Granted

    actual suspend fun providePermission(permission: Permission) { //INFO: do nothing
    }

    actual fun openAppSettings() { //INFO: do nothing
    }
}
