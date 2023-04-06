package com.tmdb.shared.core.viewModel

import com.tmdb.util.dispatcher.di.DISPATCHER_MAIN
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.qualifier.named

actual abstract class BaseViewModel: KoinComponent {
    private val dispatcherMain: CoroutineDispatcher
        get() = get(named(DISPATCHER_MAIN))

    actual val coroutineScope = CoroutineScope(dispatcherMain + SupervisorJob())

    actual fun dispose() {
        coroutineScope.cancel()
        onCleared()
    }

    protected actual open fun onCleared() {
    }
}