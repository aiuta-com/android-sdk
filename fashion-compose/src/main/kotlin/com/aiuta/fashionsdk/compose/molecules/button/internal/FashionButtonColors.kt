package com.aiuta.fashionsdk.compose.molecules.button.internal

import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.aiuta.fashionsdk.compose.tokens.AiutaTheme

internal object FashionButtonColors {
    @Composable
    fun primaryColors(theme: AiutaTheme) =
        DefaultFashionButtonColor(
            buttonColors =
                ButtonDefaults.buttonColors(
                    backgroundColor = theme.colors.brand,
                    contentColor = theme.colors.onDark,
                ),
            rippleColor = theme.colors.brand.copy(alpha = 0.1f),
        )

    @Composable
    fun primaryColors(
        backgroundColor: Color,
        contentColor: Color,
    ) = DefaultFashionButtonColor(
        buttonColors =
            ButtonDefaults.buttonColors(
                backgroundColor = backgroundColor,
                contentColor = contentColor,
            ),
        rippleColor = backgroundColor.copy(alpha = 0.1f),
    )

    @Composable
    fun secondaryColors(theme: AiutaTheme) =
        OutlineFashionButtonColor(
            buttonColors =
                ButtonDefaults.outlinedButtonColors(
                    backgroundColor = theme.colors.background,
                    contentColor = theme.colors.primary,
                ),
            borderColor = theme.colors.neutral2,
            rippleColor = theme.colors.primary.copy(alpha = 0.05f),
        )

    @Composable
    fun secondaryColors(
        backgroundColor: Color,
        contentColor: Color,
        borderColor: Color,
    ) = OutlineFashionButtonColor(
        buttonColors =
            ButtonDefaults.outlinedButtonColors(
                backgroundColor = backgroundColor,
                contentColor = contentColor,
            ),
        borderColor = borderColor,
        rippleColor = contentColor.copy(alpha = 0.05f),
    )
}
