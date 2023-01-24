package com.tmdb.data.api.config.di.module

import com.tmdb.data.api.config.url.image.contract.ImageUrlProvider
import com.tmdb.data.api.config.url.image.impl.ImageUrlProviderImpl
import com.tmdb.data.api.config.url.provider.BaseUrlProvider
import org.koin.dsl.module


val imageUrlModule = module {
    if (!baseUrlModule.isLoaded) {
        includes(baseUrlModule)
    }
    single<ImageUrlProvider>() {
        val baseUrlProvider: BaseUrlProvider = get()
        ImageUrlProviderImpl(baseUrlProvider.apiImageUrl)
    }
}