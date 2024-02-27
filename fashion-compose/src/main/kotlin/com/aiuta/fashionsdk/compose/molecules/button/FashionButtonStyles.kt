package com.aiuta.fashionsdk.compose.molecules.button

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.compose.molecules.button.internal.DefaultFashionButtonStyle
import com.aiuta.fashionsdk.compose.molecules.button.internal.FashionButtonColors
import com.aiuta.fashionsdk.compose.molecules.button.internal.FashionButtonStyle
import com.aiuta.fashionsdk.compose.molecules.button.internal.OutlineFashionButtonStyle

@Immutable
public object FashionButtonStyles {
    @Composable
    public fun inverseStyle(): FashionButtonStyle =
        DefaultFashionButtonStyle(
            colors = FashionButtonColors.inverseColors(),
        )

    @Composable
    public fun primaryStyle(): FashionButtonStyle =
        DefaultFashionButtonStyle(
            colors = FashionButtonColors.primaryColors(),
        )

    @Composable
    public fun errorStyle(): FashionButtonStyle =
        DefaultFashionButtonStyle(
            colors = FashionButtonColors.errorColors(),
        )

    @Composable
    public fun secondaryStyle(): FashionButtonStyle =
        DefaultFashionButtonStyle(
            colors = FashionButtonColors.secondaryColors(),
        )

    @Composable
    public fun transparentStyle(): FashionButtonStyle =
        DefaultFashionButtonStyle(
            colors = FashionButtonColors.transparentColors(),
        )

    @Composable
    public fun outlineStyle(): FashionButtonStyle =
        OutlineFashionButtonStyle(
            colors = FashionButtonColors.outlineColors(),
        )
}
