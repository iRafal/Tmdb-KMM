package com.tmdb.kmm.shared.ui.core.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import com.tmdb.shared.core.theme.Purple200
import com.tmdb.shared.core.theme.Purple500
import com.tmdb.shared.core.theme.Purple700
import com.tmdb.shared.core.theme.Shapes
import com.tmdb.shared.core.theme.Teal200
import com.tmdb.shared.core.theme.Typography

private val DarkColorPalette = darkColorScheme(
    primary = Purple200,
    onPrimary = Purple700,
    secondary = Teal200
)

private val LightColorPalette = lightColorScheme(
    primary = Purple500,
    onPrimary = Purple700,
    secondary = Teal200,
)

@Composable
fun Tmdb_TestTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
