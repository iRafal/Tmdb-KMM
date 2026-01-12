package com.tmdb

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import com.tmdb.app.ComposeApp
import kotlinx.browser.document

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    AppModule.start()
    
    ComposeViewport(document.body!!) {
        ComposeApp()
    }
}
