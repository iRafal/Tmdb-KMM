package com.tmdb.shared_ui.utils

import kotlinx.datetime.LocalDateTime

expect fun LocalDateTime.format(pattern: String): String