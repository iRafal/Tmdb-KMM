package com.tmdb.utils

import java.time.format.DateTimeFormatter
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toJavaLocalDate
import kotlinx.datetime.toJavaLocalDateTime

actual fun LocalDateTime.format(pattern: String): String =
    DateTimeFormatter.ofPattern(pattern).format(this.toJavaLocalDateTime())

actual fun LocalDate.format(pattern: String): String =
    DateTimeFormatter.ofPattern(pattern).format(this.toJavaLocalDate())
