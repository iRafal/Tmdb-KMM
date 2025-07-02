package com.tmdb.data.api.model.genre

import com.tmdb.data.api.model.di.UnitTestServiceLocator
import kotlinx.serialization.ExperimentalSerializationApi
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith


@OptIn(ExperimentalSerializationApi::class)
class GenreJsonParsingTest {

    @ExperimentalSerializationApi
    private val json = UnitTestServiceLocator.json

    @Test
    fun `parse genres response json`() {
        val genresResponseJson = """
        {
          "genres": [
            {
              "id": 28,
              "name": "Action"
            }
          ]
        }
        """.trimIndent()

        val expected = GenresList(listOf(Genre(id = 28, name = "Action")))
        val actual = json.decodeFromString<GenresList>(genresResponseJson)
        assertEquals(expected, actual)
    }

    @Test
    fun `parse empty genres response json object`() {
        val genresResponseJson = "{ }"
        val expected = GenresList()
        val actual = json.decodeFromString<GenresList>(genresResponseJson)
        assertEquals(expected, actual)
    }

    @Test
    fun `parse empty genres response json`() {
        val genresResponseJson = ""
        assertFailsWith<Exception> {
            json.decodeFromString<GenresList>(genresResponseJson)
        }
    }
}