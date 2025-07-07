package com.tmdb.shared.utils.permission

import com.tmdb.shared.utils.permission.common.AppPermissionsController
import com.tmdb.shared.utils.permission.common.Permission
import com.tmdb.shared.utils.permission.common.PermissionState

interface PermissionHandler {
    fun openSettingsApp()
    suspend fun handlePermission(permission: AppPermission): PermissionRequestResult
    suspend fun isPermissionGranted(permission: AppPermission): Boolean
    suspend fun getPermissionState(permission: AppPermission): PermissionState
    suspend fun requestPermission(vararg permission: AppPermission)
}

class PermissionHandlerImpl(private val permissionsController: AppPermissionsController) : PermissionHandler {
    override fun openSettingsApp() {
        permissionsController.openAppSettings()
    }

    override suspend fun handlePermission(permission: AppPermission): PermissionRequestResult {
        return if (isPermissionGranted(permission)) {
            PermissionRequestResult.GRANTED
        } else {
            requestPermission(permission)
            mapPermissionState(getPermissionState(permission))
        }
    }

    override suspend fun isPermissionGranted(permission: AppPermission): Boolean {
        return getPermissionState(permission) == PermissionState.Granted
    }

    override suspend fun getPermissionState(permission: AppPermission): PermissionState {
        return permissionsController.getPermissionState(mapPermissionType(permission))
    }

    override suspend fun requestPermission(vararg permission: AppPermission) {
        permission.forEach {
            permissionsController.providePermission(mapPermissionType(it))
        }
    }

    private fun mapPermissionType(permission: AppPermission): Permission {
        return when (permission) {
            AppPermission.Location.Approximate -> Permission.CoarseLocation
            AppPermission.Location.Fine -> Permission.Location
            AppPermission.RecordAudio -> Permission.RecordAudio
        }
    }

    private fun mapPermissionState(permissionState: PermissionState): PermissionRequestResult {
        return when (permissionState) {
            PermissionState.Granted -> PermissionRequestResult.GRANTED

            PermissionState.NotDetermined,
            PermissionState.NotGranted,
            PermissionState.Denied,
                -> PermissionRequestResult.NOT_GRANTED

            PermissionState.DeniedAlways -> PermissionRequestResult.DENIED_ALWAYS

            else -> PermissionRequestResult.NOT_GRANTED
        }
    }
}
