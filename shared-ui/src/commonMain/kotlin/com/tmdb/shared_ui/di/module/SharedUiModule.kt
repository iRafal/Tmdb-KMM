package com.tmdb.shared_ui.di.module

import com.tmdb.shared.di.module.sharedModule
import org.koin.dsl.module


fun sharedUiModule() = module {
    includes(sharedModule())
}