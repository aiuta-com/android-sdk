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
                    backgroundColor = Color.Transparent,
                    contentColor = theme.colors.primary,
                ),
            borderColor = theme.colors.neutral2,
            rippleColor = theme.colors.primary.copy(alpha = 0.05f),
        )
}
