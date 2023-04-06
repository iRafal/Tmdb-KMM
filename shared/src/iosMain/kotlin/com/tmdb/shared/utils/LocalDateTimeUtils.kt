package com.tmdb.shared.utils

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toNSDateComponents
import platform.Foundation.NSDateFormatter
import platform.Foundation.NSLocale
import platform.Foundation.NSTimeZone
import platform.Foundation.autoupdatingCurrentLocale
import platform.Foundation.localTimeZone

/**
 * Code taken form here
 * https://github.com/Kotlin/kotlinx-datetime/issues/211
 */
actual fun LocalDateTime.format(pattern: String): String {
    val dateFormatter = NSDateFormatter()
    dateFormatter.dateFormat = pattern
    dateFormatter.timeZone = NSTimeZone.localTimeZone
    dateFormatter.locale = NSLocale.autoupdatingCurrentLocale

    val date = this.toNSDateComponents().date
        ?: throw IllegalStateException("Could not convert kotlin date to NSDate $this")
    return dateFormatter.stringFromDate(date)
}

actual fun LocalDate.format(pattern: String): String {
    val dateFormatter = NSDateFormatter()
    dateFormatter.dateFormat = pattern
    dateFormatter.timeZone = NSTimeZone.localTimeZone
    dateFormatter.locale = NSLocale.autoupdatingCurrentLocale
    val date = this.toNSDateComponents().date
        ?: throw IllegalStateException("Could not convert kotlin date to NSDate $this")
    return dateFormatter.stringFromDate(date)
}