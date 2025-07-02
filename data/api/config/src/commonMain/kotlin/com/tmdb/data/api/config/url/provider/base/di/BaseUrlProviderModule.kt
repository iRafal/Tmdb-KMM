package com.tmdb.data.api.config.url.provider.base.di

import com.tmdb.data.api.config.url.provider.base.BaseUrlProvider
import com.tmdb.data.api.config.url.provider.base.BaseUrlProviderImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

fun baseUrlProviderModule() = module {
    singleOf(::BaseUrlProviderImpl) { bind<BaseUrlProvider>() }
}
