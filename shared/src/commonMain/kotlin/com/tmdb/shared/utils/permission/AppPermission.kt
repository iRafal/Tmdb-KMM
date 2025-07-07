package com.tmdb.shared.utils.permission

sealed interface AppPermission {
    data object RecordAudio : AppPermission
    interface Location {
        data object Fine : AppPermission
        data object Approximate : AppPermission
    }
}
