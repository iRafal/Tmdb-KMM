package com.tmdb.util.dispatcher

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

actual fun ioDispatcher(): CoroutineDispatcher = Dispatchers.IO