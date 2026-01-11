package com.tmdb.data.model.mopping.movie

import com.tmdb.data.api.config.url.image.contract.ImageUrlProvider
import com.tmdb.data.api.config.url.image.impl.ImageUrlProviderImpl
import com.tmdb.data.model.mapping.movie.MovieApiToDataModelMapper
import com.tmdb.data.model.mapping.movie.movieApiToDataModelMapperImpl
import com.tmdb.data.model.util.ModelUtil
import kotlin.test.Test
import kotlin.test.assertEquals

class MovieApiToDataModelMapperTest {
    private val baseUrl = "https://web.site.com"
    private val imageUrlProvider: ImageUrlProvider = ImageUrlProviderImpl(baseUrl)
    private val mapper: MovieApiToDataModelMapper = movieApiToDataModelMapperImpl(imageUrlProvider)

    @Test
    fun testMapApiToDataModel() {
        val input = ModelUtil.movieModel
        val actual = mapper.invoke(input)
        val expected = ModelUtil.movieDataModel
        assertEquals(expected, actual)
    }
}
