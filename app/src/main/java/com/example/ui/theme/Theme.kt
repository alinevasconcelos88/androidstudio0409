package com.example.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme =
    darkColorScheme(
        primary = AccentBlue,
        onPrimary = TextPrimaryDark,
        primaryContainer = DarkSurfaceVariant,
        onPrimaryContainer = TextPrimaryDark,
        secondary = AccentIndigo,
        onSecondary = TextPrimaryDark,
        tertiary = AccentCyan,
        background = DarkBackground,
        onBackground = TextPrimaryDark,
        surface = DarkSurface,
        onSurface = TextPrimaryDark,
        surfaceVariant = DarkSurfaceVariant,
        onSurfaceVariant = TextSecondaryDark,
        outline = DarkBorder
    )

private val LightColorScheme =
    lightColorScheme(
        primary = AccentBlue,
        onPrimary = TextPrimaryDark,
        primaryContainer = LightSurfaceVariant,
        onPrimaryContainer = TextPrimaryLight,
        secondary = AccentIndigo,
        onSecondary = TextPrimaryDark,
        tertiary = AccentCyan,
        background = LightBackground,
        onBackground = TextPrimaryLight,
        surface = LightSurface,
        onSurface = TextPrimaryLight,
        surfaceVariant = LightSurfaceVariant,
        onSurfaceVariant = TextSecondaryLight,
        outline = LightBorder
    )

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false, // Preserve bespoke sleek palette by default
    content: @Composable () -> Unit,
) {
  val colorScheme =
      when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
          val context = LocalContext.current
          if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
      }

  MaterialTheme(
      colorScheme = colorScheme,
      typography = Typography,
      content = content
  )
}

