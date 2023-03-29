package com.tmdb.data.db.sqldelight.adapters

import app.cash.sqldelight.ColumnAdapter
import kotlinx.datetime.LocalDateTime

class LocalDateTimeColumnAdapter : ColumnAdapter<LocalDateTime, String> {
    override fun decode(databaseValue: String): LocalDateTime = LocalDateTime.parse(databaseValue)
    override fun encode(value: LocalDateTime): String = value.toString()
}