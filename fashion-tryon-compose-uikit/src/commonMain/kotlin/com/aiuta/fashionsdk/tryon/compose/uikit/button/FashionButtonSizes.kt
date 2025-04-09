package com.aiuta.fashionsdk.tryon.compose.uikit.button

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.aiuta.fashionsdk.tryon.compose.uikit.button.internal.DefaultFashionButtonSize
import com.aiuta.fashionsdk.tryon.compose.uikit.button.internal.FashionButtonSize
import com.aiuta.fashionsdk.tryon.compose.uikit.composition.LocalTheme

@Immutable
public object FashionButtonSizes {
    private val IconSize = 20.dp
    private val IconSpacing = 4.dp

    @Composable
    public fun lSize(
        verticalPadding: Dp = 16.dp,
        horizontalPadding: Dp = 16.dp,
        iconSize: Dp = IconSize,
        iconSpacing: Dp = IconSpacing,
        shape: Shape = LocalTheme.current.button.shapes.buttonMShape,
        textStyle: TextStyle = LocalTheme.current.button.typography.buttonM,
    ): FashionButtonSize = DefaultFashionButtonSize(
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
        shape: Shape = LocalTheme.current.button.shapes.buttonSShape,
        textStyle: TextStyle = LocalTheme.current.button.typography.buttonS,
    ): FashionButtonSize = DefaultFashionButtonSize(
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
