package com.aiuta.fashionsdk.compose.molecules.button

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.aiuta.fashionsdk.compose.molecules.button.internal.DefaultFashionButtonSize
import com.aiuta.fashionsdk.compose.molecules.button.internal.FashionButtonSize
import com.aiuta.fashionsdk.compose.tokens.composition.LocalTheme

@Immutable
public object FashionButtonSizes {
    private val IconSize = 20.dp
    private val IconSpacing = 8.dp

    @Composable
    public fun lSize(
        verticalPadding: Dp = 16.dp,
        horizontalPadding: Dp = 16.dp,
        iconSize: Dp = IconSize,
        iconSpacing: Dp = IconSpacing,
        shape: Shape = LocalTheme.current.shapes.buttonL,
        textStyle: TextStyle = LocalTheme.current.typography.button,
    ): FashionButtonSize =
        DefaultFashionButtonSize(
            paddingValues =
                PaddingValues(
                    vertical = verticalPadding,
                    horizontal = horizontalPadding,
                ),
            iconSize = iconSize,
            iconSpacing = iconSpacing,
            shape = shape,
            textStyle = textStyle,
        )

    @Composable
    public fun mSize(
        verticalPadding: Dp = 12.dp,
        horizontalPadding: Dp = 24.dp,
        iconSize: Dp = IconSize,
        iconSpacing: Dp = IconSpacing,
        shape: Shape = LocalTheme.current.shapes.buttonM,
        textStyle: TextStyle = LocalTheme.current.typography.smallButton,
    ): FashionButtonSize =
        DefaultFashionButtonSize(
            paddingValues =
                PaddingValues(
                    vertical = verticalPadding,
                    horizontal = horizontalPadding,
                ),
            iconSize = iconSize,
            iconSpacing = iconSpacing,
            shape = shape,
            textStyle = textStyle,
        )
}
