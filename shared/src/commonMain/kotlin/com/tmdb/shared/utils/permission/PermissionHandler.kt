package com.tmdb.shared.utils.permission

import com.tmdb.shared.utils.permission.common.AppPermissionsController
import com.tmdb.shared.utils.permission.common.CoarseLocation
import com.tmdb.shared.utils.permission.common.Denied
import com.tmdb.shared.utils.permission.common.DeniedAlways
import com.tmdb.shared.utils.permission.common.Granted
import com.tmdb.shared.utils.permission.common.Location
import com.tmdb.shared.utils.permission.common.NotDetermined
import com.tmdb.shared.utils.permission.common.NotGranted
import com.tmdb.shared.utils.permission.common.Permission
import com.tmdb.shared.utils.permission.common.PermissionState
import com.tmdb.shared.utils.permission.common.RecordAudio

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
        return getPermissionState(permission) == Granted
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
            AppPermission.Location.Approximate -> CoarseLocation
            AppPermission.Location.Fine -> Location
            AppPermission.RecordAudio -> RecordAudio
        }
    }

    private fun mapPermissionState(permissionState: PermissionState): PermissionRequestResult {
        return when (permissionState) {
            Granted -> PermissionRequestResult.GRANTED

            NotDetermined,
            NotGranted,
            Denied, -> PermissionRequestResult.NOT_GRANTED

            DeniedAlways -> PermissionRequestResult.DENIED_ALWAYS

            else -> PermissionRequestResult.NOT_GRANTED
        }
    }
}
