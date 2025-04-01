package com.aiuta.fashionsdk.compose.molecules.button

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.aiuta.fashionsdk.compose.molecules.button.internal.DefaultFashionButtonStyle
import com.aiuta.fashionsdk.compose.molecules.button.internal.FashionButtonColors
import com.aiuta.fashionsdk.compose.molecules.button.internal.FashionButtonColors.transparentColors
import com.aiuta.fashionsdk.compose.molecules.button.internal.FashionButtonStyle
import com.aiuta.fashionsdk.compose.molecules.button.internal.GradientButtonStyle
import com.aiuta.fashionsdk.compose.molecules.button.internal.SecondaryFashionButtonStyle
import com.aiuta.fashionsdk.compose.tokens.AiutaTheme

@Immutable
public object FashionButtonStyles {
    @Composable
    public fun primaryStyle(theme: AiutaTheme): FashionButtonStyle = DefaultFashionButtonStyle(
        colors = FashionButtonColors.primaryColors(theme = theme),
    )

    @Composable
    public fun primaryStyle(
        backgroundColor: Color,
        contentColor: Color,
    ): FashionButtonStyle = DefaultFashionButtonStyle(
        colors =
        FashionButtonColors.primaryColors(
            backgroundColor = backgroundColor,
            contentColor = contentColor,
        ),
    )

    @Composable
    public fun gradientColors(
        contentColor: Color,
        gradientBackground: Brush,
    ): FashionButtonStyle = GradientButtonStyle(
        colors = transparentColors(contentColor = contentColor),
        gradientBackground = gradientBackground,
    )

    @Composable
    public fun secondaryStyle(theme: AiutaTheme): FashionButtonStyle = SecondaryFashionButtonStyle(
        colors = FashionButtonColors.secondaryColors(theme = theme),
    )

    @Composable
    public fun secondaryStyle(
        backgroundColor: Color,
        contentColor: Color,
        borderColor: Color,
    ): FashionButtonStyle = SecondaryFashionButtonStyle(
        colors =
        FashionButtonColors.secondaryColors(
            backgroundColor = backgroundColor,
            contentColor = contentColor,
            borderColor = borderColor,
        ),
    )
}
