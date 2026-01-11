package com.tmdb.utils.permission.common

actual object Granted : PermissionState()
actual object Denied : PermissionState()
actual object DeniedAlways : PermissionState()
actual object NotGranted : PermissionState()
actual object NotDetermined : PermissionState()

actual sealed class PermissionState
