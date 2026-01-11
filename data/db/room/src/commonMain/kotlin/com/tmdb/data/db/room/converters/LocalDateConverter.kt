package com.tmdb.data.db.room.converters

import androidx.room.TypeConverter
import kotlinx.datetime.LocalDate

object LocalDateConverter {

    @TypeConverter
    fun fromLocalDateTime(dateTime: LocalDate?): String? = dateTime?.toString()

    @TypeConverter
    fun fromTimeStamp(timeStamp: String?): LocalDate? = timeStamp?.let { LocalDate.parse(it) }
}
