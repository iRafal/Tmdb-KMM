package com.tmdb.util.dispatcher.di

import com.tmdb.util.dispatcher.mainDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val DISPATCHER_MAIN = "DISPATCHER_MAIN"
const val DISPATCHER_DEFAULT = "DISPATCHER_DEFAULT"
const val DISPATCHER_UNCONFINED = "DISPATCHER_UNCONFINED"
const val DISPATCHER_IO = "DISPATCHER_IO"

fun dispatchersModule() = module {
    single<CoroutineDispatcher>(named(DISPATCHER_MAIN)) { mainDispatcher() }
    single<CoroutineDispatcher>(named(DISPATCHER_DEFAULT)) { Dispatchers.Default }
    single<CoroutineDispatcher>(named(DISPATCHER_UNCONFINED)) { mainDispatcher() }
    single<CoroutineDispatcher>(named(DISPATCHER_IO)) { Dispatchers.IO }
}
