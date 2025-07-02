package com.tmdb.data.api.config.url.image.di

import com.tmdb.data.api.config.url.image.contract.ImageUrlProvider
import com.tmdb.data.api.config.url.image.impl.ImageUrlProviderImpl
import com.tmdb.data.api.config.url.provider.base.BaseUrlProvider
import com.tmdb.data.api.config.url.provider.base.di.baseUrlProviderModule
import org.koin.dsl.module


fun imageUrlModule() = module {
    includes(baseUrlProviderModule())

    single<ImageUrlProvider>() {
        val baseUrlProvider: BaseUrlProvider = get()
        ImageUrlProviderImpl(baseUrlProvider.apiImageUrl)
    }
}
