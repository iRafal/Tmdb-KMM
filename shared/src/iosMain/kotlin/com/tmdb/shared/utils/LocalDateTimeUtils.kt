package com.tmdb.shared.utils

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toNSDateComponents
import platform.Foundation.NSCalendar
import platform.Foundation.NSDateFormatter
import platform.Foundation.NSLocale
import platform.Foundation.currentLocale

/**
 * Code taken form here
 * https://github.com/Kotlin/kotlinx-datetime/issues/211
 */
actual fun LocalDateTime.format(pattern: String): String {
    val date = NSCalendar.currentCalendar.dateFromComponents(toNSDateComponents())
        ?: throw IllegalStateException("Could not convert kotlin date to NSDate $this")
    return NSDateFormatter().apply {
        dateFormat = NSDateFormatter.dateFormatFromTemplate(pattern, 0u, NSLocale.currentLocale)!!
    }.stringFromDate(date)
}

actual fun LocalDate.format(pattern: String): String {
    val date = NSCalendar.currentCalendar.dateFromComponents(toNSDateComponents())
        ?: throw IllegalStateException("Could not convert kotlin date to NSDate $this")
    return NSDateFormatter().apply {
        dateFormat = NSDateFormatter.dateFormatFromTemplate(pattern, 0u, NSLocale.currentLocale)!!
    }.stringFromDate(date)
}
