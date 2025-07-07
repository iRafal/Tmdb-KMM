package com.tmdb.shared.utils.permission.common

import dev.icerock.moko.permissions.PermissionState as MokoPermissionState

actual object Granted : PermissionState(MokoPermissionState.Granted)
actual object Denied : PermissionState(MokoPermissionState.Denied)
actual object DeniedAlways : PermissionState(MokoPermissionState.DeniedAlways)
actual object NotGranted : PermissionState(MokoPermissionState.NotGranted)
actual object NotDetermined : PermissionState(MokoPermissionState.NotDetermined)

actual sealed class PermissionState(val value: MokoPermissionState)
