package com.tmdb.data.db.sqldelight.adapters

import app.cash.sqldelight.ColumnAdapter
import kotlinx.datetime.LocalDate

class LocalDateColumnAdapter : ColumnAdapter<LocalDate, String> {
    override fun decode(databaseValue: String): LocalDate = LocalDate.parse(databaseValue)
    override fun encode(value: LocalDate): String = value.toString()
}