package com.tmdb.shared.utils.permission.common

import dev.icerock.moko.permissions.PermissionsController
import dev.icerock.moko.permissions.location.COARSE_LOCATION
import dev.icerock.moko.permissions.location.LOCATION
import dev.icerock.moko.permissions.microphone.RECORD_AUDIO
import dev.icerock.moko.permissions.Permission as MokoPermission
import dev.icerock.moko.permissions.PermissionState as MokoPermissionState

actual class AppPermissionsController(private val delegate: PermissionsController) {
    actual suspend fun getPermissionState(permission: Permission): PermissionState {
        val platformPermissionState = when (permission) {
            CoarseLocation -> delegate.getPermissionState(MokoPermission.COARSE_LOCATION)
            Location -> delegate.getPermissionState(MokoPermission.LOCATION)
            RecordAudio -> delegate.getPermissionState(MokoPermission.RECORD_AUDIO)
        }
        return when (platformPermissionState) {
            MokoPermissionState.Granted -> Granted
            MokoPermissionState.Denied -> Denied
            MokoPermissionState.DeniedAlways -> DeniedAlways
            MokoPermissionState.NotGranted -> NotGranted
            MokoPermissionState.NotDetermined -> NotDetermined
        }
    }

    actual suspend fun providePermission(permission: Permission) {
        delegate.providePermission(permission.value)
    }

    actual fun openAppSettings() {
        delegate.openAppSettings()
    }
}
