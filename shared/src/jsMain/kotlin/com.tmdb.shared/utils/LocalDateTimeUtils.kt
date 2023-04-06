package com.tmdb.shared.utils

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.internal.JSJoda.DateTimeFormatter
import kotlinx.datetime.internal.JSJoda.TemporalAccessor

//INFO https://js-joda.github.io/js-joda/manual/formatting.html

actual fun LocalDateTime.format(pattern: String): String {
    return DateTimeFormatter.ofPattern(pattern).format(asDynamic() as TemporalAccessor) //TODO check
}

actual fun LocalDate.format(pattern: String): String {
    return DateTimeFormatter.ofPattern(pattern).format(asDynamic() as TemporalAccessor) //TODO check
}