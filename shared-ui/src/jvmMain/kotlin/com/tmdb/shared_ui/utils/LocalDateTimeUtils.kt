package com.tmdb.shared_ui.utils

import java.time.format.DateTimeFormatter

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toJavaLocalDateTime

actual fun LocalDateTime.format(pattern: String): String =
    DateTimeFormatter.ofPattern(pattern).format(this.toJavaLocalDateTime())