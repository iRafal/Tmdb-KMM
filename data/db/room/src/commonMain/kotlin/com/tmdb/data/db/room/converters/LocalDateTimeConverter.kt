package com.tmdb.data.db.room.converters

import androidx.room.TypeConverter
import kotlinx.datetime.LocalDateTime

object LocalDateTimeConverter {
    @TypeConverter
    fun fromLocalDateTime(dateTime: LocalDateTime?): String? = dateTime?.toString()

    @TypeConverter
    fun fromTimeStamp(timeStamp: String?): LocalDateTime? = timeStamp?.let { LocalDateTime.parse(it) }
}
