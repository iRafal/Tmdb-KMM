package com.tmdb.app.init

import co.touchlab.kermit.Logger
import co.touchlab.kermit.Severity
import kotlin.experimental.ExperimentalNativeApi
import kotlin.experimental.ExperimentalObjCName
import kotlin.native.ObjCName
import kotlin.native.Platform

@OptIn(ExperimentalObjCName::class)
@ObjCName("IosLogInitializer")
object IosLogInitializer {
    @OptIn(ExperimentalNativeApi::class)
    @ObjCName("initialize")
    fun initialize() {
        if (Platform.isDebugBinary) {
            Logger.setMinSeverity(Severity.Verbose)
        }
    }
}
