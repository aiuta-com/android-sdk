package com.aiuta.fashionsdk.compose.molecules.button.internal

import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
internal class FashionRippleTheme(private val rippleColor: Color) : RippleTheme {
    @Composable
    override fun defaultColor(): Color {
        return RippleTheme.defaultRippleColor(
            contentColor = rippleColor,
            lightTheme = true,
        )
    }

    @Composable
    override fun rippleAlpha(): RippleAlpha {
        return RippleTheme.defaultRippleAlpha(
            contentColor = rippleColor,
            lightTheme = true,
        )
    }
}
