package com.tmdb.shared_ui.core.compose

import android.R.drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter

@Composable
internal actual fun RemoteImageInternal(
    imageUrl: String,
    modifier: Modifier,
    contentScale: ContentScale,
    placeholder: Painter?,
    contentDescription: String?
) {
//    val painter = rememberAsyncImagePainter(
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
