package com.core.designsystem.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val primary = Color(0xFFFF040B)

val black202020 = Color(0xFF202020)
val black000000 = Color(0xFF000000)
val whiteFFFFFF = Color(0xFFFFFFFF)
val gray100 = Color(0xFFEEEEEE)
val redF34949 = Color(0xFFF34949)

val DarkColorScheme = darkColorScheme(
    primary = primary,
    tertiary = whiteFFFFFF,
    tertiaryContainer = gray100,
    errorContainer = redF34949,
    background = black000000
)

val LightColorScheme = lightColorScheme(
    primary = primary,
    tertiary = black000000,
    tertiaryContainer = gray100,
    errorContainer = redF34949,
    background = whiteFFFFFF,
)