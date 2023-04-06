package com.tmdb.shared_ui.core.compose

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import com.seiko.imageloader.LocalImageLoader
import com.seiko.imageloader.rememberAsyncImagePainter

@Composable
internal actual fun RemoteImageInternal(
    imageUrl: String,
    modifier: Modifier,
    contentScale: ContentScale,
    placeholder: Painter?,
    contentDescription: String?
) {
    CompositionLocalProvider(
        LocalImageLoader provides LocalImageLoader.current,
    ) {
        val resource =
            rememberAsyncImagePainter(url = imageUrl, imageLoader = LocalImageLoader.current)
        Image(
            painter = resource,
            contentScale = contentScale,
            contentDescription = contentDescription,
            modifier = modifier,
        )
    }
}

