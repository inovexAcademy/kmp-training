package de.inovex.kmp_training.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Warm amber/terracotta color palette
private val Amber50 = Color(0xFFFFF8E1)
private val Amber100 = Color(0xFFFFECB3)
private val Amber200 = Color(0xFFFFE082)
private val Amber300 = Color(0xFFFFD54F)
private val Amber400 = Color(0xFFFFCA28)
private val Amber500 = Color(0xFFFFC107)
private val Amber600 = Color(0xFFFFB300)
private val Amber700 = Color(0xFFFFA000)
private val Amber800 = Color(0xFFFF8F00)
private val Amber900 = Color(0xFFFF6F00)

private val Terracotta = Color(0xFFE07A5F)
private val TerracottaDark = Color(0xFFD65F43)
private val Sage = Color(0xFF81B29A)
private val Sand = Color(0xFFF2CC8F)
private val Navy = Color(0xFF3D405B)
private val Cream = Color(0xFFF4F1DE)

// Priority colors
val PriorityLow = Color(0xFF4CAF50)
val PriorityMedium = Color(0xFFFF9800)
val PriorityHigh = Color(0xFFF44336)

private val LightColorScheme = lightColorScheme(
    primary = Terracotta,
    onPrimary = Color.White,
    primaryContainer = Color(0xFFFFDAD3),
    onPrimaryContainer = Color(0xFF3B0907),
    secondary = Sage,
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFCCE8DC),
    onSecondaryContainer = Color(0xFF0A1F16),
    tertiary = Sand,
    onTertiary = Color(0xFF3F2E00),
    tertiaryContainer = Color(0xFFFFE4B2),
    onTertiaryContainer = Color(0xFF251A00),
    background = Cream,
    onBackground = Navy,
    surface = Color.White,
    onSurface = Navy,
    surfaceVariant = Color(0xFFF5DDDA),
    onSurfaceVariant = Color(0xFF534341),
    outline = Color(0xFF857371),
    error = Color(0xFFBA1A1A),
    onError = Color.White,
    errorContainer = Color(0xFFFFDAD6),
    onErrorContainer = Color(0xFF410002)
)

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFFFB4A8),
    onPrimary = Color(0xFF5C1A12),
    primaryContainer = TerracottaDark,
    onPrimaryContainer = Color(0xFFFFDAD3),
    secondary = Color(0xFFB0CFC0),
    onSecondary = Color(0xFF1C352B),
    secondaryContainer = Color(0xFF324B41),
    onSecondaryContainer = Color(0xFFCCE8DC),
    tertiary = Color(0xFFE5C68C),
    onTertiary = Color(0xFF402D00),
    tertiaryContainer = Color(0xFF5C4300),
    onTertiaryContainer = Color(0xFFFFE4B2),
    background = Color(0xFF1A1110),
    onBackground = Color(0xFFF1DFDC),
    surface = Color(0xFF1A1110),
    onSurface = Color(0xFFF1DFDC),
    surfaceVariant = Color(0xFF534341),
    onSurfaceVariant = Color(0xFFD8C2BF),
    outline = Color(0xFFA08C8A),
    error = Color(0xFFFFB4AB),
    onError = Color(0xFF690005),
    errorContainer = Color(0xFF93000A),
    onErrorContainer = Color(0xFFFFDAD6)
)

@Composable
fun TaskManagerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    
    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}

