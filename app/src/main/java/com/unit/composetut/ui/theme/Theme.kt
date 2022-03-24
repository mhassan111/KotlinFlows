package com.unit.composetut.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.unit.composetut.R
import kotlin.reflect.typeOf

private val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200
)

private val LightColorPalette = lightColors(
    primary = Purple500,
    primaryVariant = Purple700,
    secondary = Teal200

    /* Other default colors to override
background = Color.White,
surface = Color.White,
onPrimary = Color.White,
onSecondary = Color.Black,
onBackground = Color.Black,
onSurface = Color.Black,
*/
)

private val LoginLightColorPalette = lightColors(
    primary = MainColor,
    primaryVariant = MainColor,
    secondary = MainColor
)

private val LibraryLightColorPalette = lightColors(
    primary = BrandColor,
    primaryVariant = BrandColor,
    secondary = BrandColor
)

@Composable
fun ComposeTutTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}

@Composable
fun LoginTutTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = LoginLightColorPalette,
        typography = loginTypography,
        shapes = Shapes,
        content = content
    )
}

@Composable
fun LibraryTutTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = LibraryLightColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}

val nunitoFamily = FontFamily(
    Font(R.font.nunito_light, FontWeight.Light),
    Font(R.font.nunito_regular, FontWeight.Normal),
    Font(R.font.nunito_regular, FontWeight.Medium),
    Font(R.font.nunito_bold, FontWeight.Bold)
)

val nunitoTypography = Typography(
    defaultFontFamily = nunitoFamily
)

private val NunitoLightColorPalette = lightColors(
    primary = Orange,
    primaryVariant = Orange,
    secondary = Orange
)

@Composable
fun GroceryAppTheme(content: @Composable() () -> Unit) {
    MaterialTheme(
        colors = NunitoLightColorPalette,
        typography = nunitoTypography,
        shapes = Shapes,
        content = content
    )
}