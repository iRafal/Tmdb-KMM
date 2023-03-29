package com.tmdb.data.db.sqldelight.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val DISPATCHER_TEST_STANDARD = "DISPATCHER_TEST_STANDARD"
const val DISPATCHER_TEST_UNCONFINED = "DISPATCHER_TEST_UNCONFINED"
const val DISPATCHER_IO = "DISPATCHER_IO"

@OptIn(ExperimentalCoroutinesApi::class)
val koinTestModule = module {
    single<CoroutineDispatcher>(named(DISPATCHER_TEST_STANDARD)) { StandardTestDispatcher() }
    single<CoroutineDispatcher>(named(DISPATCHER_TEST_UNCONFINED)) { UnconfinedTestDispatcher() }
    single(named(DISPATCHER_IO)) { Dispatchers.Default }
}