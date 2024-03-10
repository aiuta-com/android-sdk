package com.aiuta.fashionsdk.compose.molecules.button

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import com.aiuta.fashionsdk.compose.molecules.button.internal.DefaultFashionButtonStyle
import com.aiuta.fashionsdk.compose.molecules.button.internal.FashionButtonColors
import com.aiuta.fashionsdk.compose.molecules.button.internal.FashionButtonStyle
import com.aiuta.fashionsdk.compose.molecules.button.internal.OutlineFashionButtonStyle
import com.aiuta.fashionsdk.compose.tokens.AiutaTheme

@Immutable
public object FashionButtonStyles {
    @Composable
    public fun primaryStyle(theme: AiutaTheme): FashionButtonStyle =
        DefaultFashionButtonStyle(
            colors = FashionButtonColors.primaryColors(theme = theme),
        )

    @Composable
    public fun primaryStyle(
        backgroundColor: Color,
        contentColor: Color,
    ): FashionButtonStyle =
        DefaultFashionButtonStyle(
            colors =
                FashionButtonColors.primaryColors(
                    backgroundColor = backgroundColor,
                    contentColor = contentColor,
                ),
        )

    @Composable
    public fun outlineStyle(theme: AiutaTheme): FashionButtonStyle =
        OutlineFashionButtonStyle(
            colors = FashionButtonColors.outlineColors(theme = theme),
        )
}
