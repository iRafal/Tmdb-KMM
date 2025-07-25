package com.tmdb.data.api.model.person

import com.tmdb.data.api.model.di.UnitTestServiceLocator
import com.tmdb.data.api.model.util.ModelUtil
import kotlinx.serialization.ExperimentalSerializationApi
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

@OptIn(ExperimentalSerializationApi::class)
class PersonJsonParsingTest {

    @ExperimentalSerializationApi
    private val json = UnitTestServiceLocator.json

    @Test
    fun `parse person`() {
        val personJson = """
        {
            "birthday": "1963-12-18",
            "known_for_department": "Acting",
            "deathday": null,
            "id": 287,
            "name": "Brad Pitt",
            "also_known_as": [
                "برد پیت",
                "Бред Питт",
                "Бред Пітт",
                "Buratto Pitto",
                "Брэд Питт",
                "畢·彼特",
                "ブラッド・ピット",
                "브래드 피트",
                "براد بيت",
                "แบรด พิตต์"
            ],
            "gender": 2,
            "biography": "William Bradley \"Brad\" Pitt (born December 18, 1963) is an American actor and film producer. Pitt has received two Academy Award nominations and four Golden Globe Award nominations, winning one. He has been described as one of the world's most attractive men, a label for which he has received substantial media attention. Pitt began his acting career with television guest appearances, including a role on the CBS prime-time soap opera Dallas in 1987. He later gained recognition as the cowboy hitchhiker who seduces Geena Davis's character in the 1991 road movie Thelma & Louise. Pitt's first leading roles in big-budget productions came with A River Runs Through It (1992) and Interview with the Vampire (1994). He was cast opposite Anthony Hopkins in the 1994 drama Legends of the Fall, which earned him his first Golden Globe nomination. In 1995 he gave critically acclaimed performances in the crime thriller Seven and the science fiction film 12 Monkeys, the latter securing him a Golden Globe Award for Best Supporting Actor and an Academy Award nomination.\n\nFour years later, in 1999, Pitt starred in the cult hit Fight Club. He then starred in the major international hit as Rusty Ryan in Ocean's Eleven (2001) and its sequels, Ocean's Twelve (2004) and Ocean's Thirteen (2007). His greatest commercial successes have been Troy (2004) and Mr. & Mrs. Smith (2005).\n\nPitt received his second Academy Award nomination for his title role performance in the 2008 film The Curious Case of Benjamin Button. Following a high-profile relationship with actress Gwyneth Paltrow, Pitt was married to actress Jennifer Aniston for five years. Pitt lives with actress Angelina Jolie in a relationship that has generated wide publicity. He and Jolie have six children—Maddox, Pax, Zahara, Shiloh, Knox, and Vivienne.\n\nSince beginning his relationship with Jolie, he has become increasingly involved in social issues both in the United States and internationally. Pitt owns a production company named Plan B Entertainment, whose productions include the 2007 Academy Award winning Best Picture, The Departed.",
            "popularity": 10.647,
            "place_of_birth": "Shawnee, Oklahoma, USA",
            "profile_path": "/kU3B75TyRiCgE270EyZnHjfivoq.jpg",
            "adult": false,
            "imdb_id": "nm0000093",
            "homepage": null
        }
        """.trimIndent()

        val expected = ModelUtil.personModel

        val actual = json.decodeFromString<Person>(personJson)
        assertEquals(expected, actual)
    }

    @Test
    fun `parse empty movie json object`() {
        val personJson = "{ }"
        val expected = Person()
        val actual = json.decodeFromString<Person>(personJson)
        assertEquals(expected, actual)
    }

    @Test
    fun `parse empty person json`() {
        val personJson = ""
        val expected: Person? = null
        assertFailsWith<Exception> {
            val actual = json.decodeFromString<Person>(personJson)
            assertEquals(expected, actual)
        }
    }
}