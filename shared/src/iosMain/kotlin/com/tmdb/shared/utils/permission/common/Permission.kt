package com.tmdb.shared.utils.permission.common

import dev.icerock.moko.permissions.location.COARSE_LOCATION
import dev.icerock.moko.permissions.location.LOCATION
import dev.icerock.moko.permissions.microphone.RECORD_AUDIO
import dev.icerock.moko.permissions.Permission as MokoPermission

actual object CoarseLocation : Permission(MokoPermission.COARSE_LOCATION)
actual object Location : Permission(MokoPermission.LOCATION)
actual object RecordAudio : Permission(MokoPermission.RECORD_AUDIO)

actual sealed class Permission(val value: MokoPermission)
