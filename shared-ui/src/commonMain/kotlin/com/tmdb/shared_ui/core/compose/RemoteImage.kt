package com.tmdb.shared_ui.core.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale

@Composable
internal expect fun RemoteImageInternal(
    imageUrl: String,
    modifier: Modifier,
    contentScale: ContentScale,
    placeholder: Painter?,
    contentDescription: String?
)

@Composable
fun RemoteImage(
    imageUrl: String,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Fit,
    placeholder: Painter? = null,
    contentDescription: String? = null
) {
    RemoteImageInternal(imageUrl, modifier, contentScale, placeholder, contentDescription)
}
