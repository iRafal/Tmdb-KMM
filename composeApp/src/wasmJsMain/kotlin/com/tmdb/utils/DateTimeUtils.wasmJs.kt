package com.tmdb.utils

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.number

actual fun LocalDateTime.format(pattern: String): String {
    return formatDateTime(
        year = year,
        month = month.number,
        day = day,
        hour = hour,
        minute = minute,
        second = second,
        pattern = pattern
    )
}

actual fun LocalDate.format(pattern: String): String {
    return formatDateTime(
        year = year,
        month = month.number,
        day = day,
        hour = 0,
        minute = 0,
        second = 0,
        pattern = pattern
    )
}

private fun formatDateTime(
    year: Int,
    month: Int,
    day: Int,
    hour: Int,
    minute: Int,
    second: Int,
    pattern: String
): String {
    return pattern
        .replace("yyyy", year.toString().padStart(4, '0'))
        .replace("yy", (year % 100).toString().padStart(2, '0'))
        .replace("MM", month.toString().padStart(2, '0'))
        .replace("M", month.toString())
        .replace("dd", day.toString().padStart(2, '0'))
        .replace("d", day.toString())
        .replace("HH", hour.toString().padStart(2, '0'))
        .replace("H", hour.toString())
        .replace("hh", ((hour % 12).takeIf { it != 0 } ?: 12).toString().padStart(2, '0'))
        .replace("h", ((hour % 12).takeIf { it != 0 } ?: 12).toString())
        .replace("mm", minute.toString().padStart(2, '0'))
        .replace("m", minute.toString())
        .replace("ss", second.toString().padStart(2, '0'))
        .replace("s", second.toString())
        .replace("a", if (hour < 12) "AM" else "PM")
}
