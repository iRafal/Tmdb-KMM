package com.tmdb.utils.permission.common

expect object Granted: PermissionState
expect object Denied: PermissionState
expect object DeniedAlways: PermissionState
expect object NotDetermined: PermissionState
expect object NotGranted: PermissionState

expect sealed class PermissionState
