package com.tmdb.shared_ui.utils

import platform.Foundation.NSCalendar
import platform.Foundation.NSDateFormatter

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalDate

/**
 * Code taken form here
 * https://github.com/Kotlin/kotlinx-datetime/issues/211
 */
actual fun LocalDateTime.format(pattern: String): String {
    val dateFormatter = NSDateFormatter()
    dateFormatter.dateFormat = format
    return dateFormatter.stringFromDate(toNSDate(NSCalendar.currentCalendar)
        ?: throw IllegalStateException("Could not convert kotlin date to NSDate $this")
    )
}

actual fun LocalDate.format(pattern: String): String {
    val dateFormatter = NSDateFormatter()
    dateFormatter.dateFormat = format
    return dateFormatter.stringFromDate(toNSDate(NSCalendar.currentCalendar)
        ?: throw IllegalStateException("Could not convert kotlin date to NSDate $this")
    )
}