package com.tmdb.data.api.config.url.provider

import com.tmdb.data.api.config.DataApiConfigBuildKonfig


class BaseUrlProviderImpl : BaseUrlProvider {
    override val discoverApiUrl: String = DataApiConfigBuildKonfig.API_BASE_URL
    override val genreApiUrl: String = DataApiConfigBuildKonfig.API_BASE_URL
    override val movieApiUrl: String = DataApiConfigBuildKonfig.API_BASE_URL
    override val personApiUrl: String = DataApiConfigBuildKonfig.API_BASE_URL
    override val apiImageUrl: String = DataApiConfigBuildKonfig.API_IMAGE_URL
}