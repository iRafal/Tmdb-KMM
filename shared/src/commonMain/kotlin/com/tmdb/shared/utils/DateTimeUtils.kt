package com.tmdb.shared.utils

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime

expect fun LocalDateTime.format(pattern: String): String
expect fun LocalDate.format(pattern: String): String