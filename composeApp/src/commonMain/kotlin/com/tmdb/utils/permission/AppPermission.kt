package com.tmdb.utils.permission

sealed interface AppPermission {
    data object RecordAudio : AppPermission
    interface Location {
        data object Fine : AppPermission
        data object Approximate : AppPermission
    }
}
