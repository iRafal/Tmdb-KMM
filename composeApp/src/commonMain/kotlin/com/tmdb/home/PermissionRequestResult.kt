package com.tmdb.home

enum class PermissionRequestResult {
    GRANTED, NOT_GRANTED, DENIED_ALWAYS;

    val isGranted: Boolean
        get() = this == GRANTED

    val isDeniedAlways: Boolean
        get() = this == DENIED_ALWAYS
}
