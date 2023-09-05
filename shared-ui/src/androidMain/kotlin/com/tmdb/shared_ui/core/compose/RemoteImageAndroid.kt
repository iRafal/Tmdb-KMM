package com.tmdb.shared_ui.core.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage

@Composable
internal actual fun RemoteImageInternal(
    imageUrl: String,
    modifier: Modifier,
    contentScale: ContentScale,
    placeholder: Painter?,
    contentDescription: String?
) {
//    val painter = rememberImagePainter(
//        model = imageUrl,
//        placeholder = placeholder
//    )
//    Image(
//        painter = painter,
//        contentDescription = contentDescription,
//        contentScale = contentScale,
//        modifier = modifier
//    )

    AsyncImage(
        model = imageUrl,
        modifier = modifier,
        placeholder = placeholder,
        contentDescription = null
    )
}
