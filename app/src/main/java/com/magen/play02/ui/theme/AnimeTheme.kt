package com.magen.play02.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val AnimeDarkColors = darkColorScheme(
    primary = Color(0xFF6A00EE),
    primaryContainer = Color(0xFF4B0099),
    secondary = Color(0xFF2DFFDB),
    secondaryContainer = Color(0xFF00A896),
    background = Color(0xFF1A1A2E),
    surface = Color(0xFF16213E),
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.White,
    onSurface = Color.White
)

private val AnimeLightColors = lightColorScheme(
    primary = Color(0xFF6A00EE),
    primaryContainer = Color(0xFFD5B3FF),
    secondary = Color(0xFF2D9CDB),
    secondaryContainer = Color(0xFFB3E5FC),
    background = Color(0xFFF5F5F5),
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black
)

@Composable
fun AnimeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        AnimeDarkColors
    } else {
        AnimeLightColors
    }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography(),
        content = content
    )
}