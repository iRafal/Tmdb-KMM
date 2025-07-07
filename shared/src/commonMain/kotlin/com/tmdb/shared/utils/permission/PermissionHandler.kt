package com.tmdb.shared.utils.permission

import dev.icerock.moko.permissions.Permission
import dev.icerock.moko.permissions.PermissionState
import dev.icerock.moko.permissions.PermissionsController
import dev.icerock.moko.permissions.location.COARSE_LOCATION
import dev.icerock.moko.permissions.location.LOCATION
import dev.icerock.moko.permissions.microphone.RECORD_AUDIO

interface PermissionHandler {
    fun openSettingsApp()
    suspend fun handlePermission(permission: AppPermission): PermissionRequestResult
    suspend fun isPermissionGranted(permission: AppPermission): Boolean
    suspend fun getPermissionState(permission: AppPermission): PermissionState
    suspend fun requestPermission(vararg permission: AppPermission)
}

//expect class AppPermissionsController {
//    suspend fun getPermissionState(
//        permission: AppPermissionsControllerPermission,
//    ): AppPermissionsControllerPermissionState
//
//    suspend fun providePermission(permission: AppPermissionsControllerPermission)
//    fun openAppSettings()
//}
//
//expect class AppPermissionsControllerPermission
//expect class AppPermissionsControllerPermissionState

class PermissionHandlerImpl(private val permissionsController: PermissionsController) : PermissionHandler {
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
            AppPermission.Location.Approximate -> Permission.COARSE_LOCATION
            AppPermission.Location.Fine -> Permission.LOCATION
            AppPermission.RecordAudio -> Permission.RECORD_AUDIO
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
        }
    }
}
