package com.tmdb.util.dispatcher

import kotlinx.coroutines.CoroutineDispatcher

expect fun mainDispatcher(): CoroutineDispatcher
