package com.example.restaurants.ui.theme

import android.app.Activity
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val ColorScheme = ColorScheme(
    primary = primary,
    onPrimary = onPrimary,
    primaryContainer = Color.Unspecified,
    onPrimaryContainer = Color.Unspecified,
    inversePrimary = Color.Unspecified,
    secondary = secondary,
    onSecondary = Color.Unspecified,
    secondaryContainer = Color.Unspecified,
    onSecondaryContainer = Color.Unspecified,
    tertiary = Color.Unspecified,
    onTertiary = Color.Unspecified,
    tertiaryContainer = Color.Unspecified,
    onTertiaryContainer = Color.Unspecified,
    background = background,
    onBackground = onBackground,
    surface = Color.Unspecified,
    onSurface = Color.Unspecified,
    surfaceVariant = Color.Unspecified,
    onSurfaceVariant = Color.Unspecified,
    surfaceTint = Color.Unspecified,
    inverseSurface = Color.Unspecified,
    inverseOnSurface = Color.Unspecified,
    error = Color.Unspecified,
    onError = Color.Unspecified,
    errorContainer = Color.Unspecified,
    onErrorContainer = Color.Unspecified,
    outline = Color.Unspecified,
    outlineVariant = Color.Unspecified,
    scrim = Color.Unspecified,
)

@Composable
fun RestaurantsTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = ColorScheme

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            window.navigationBarColor = colorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = true
            WindowCompat.getInsetsController(window, view).isAppearanceLightNavigationBars = false
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        content = content
    )
}