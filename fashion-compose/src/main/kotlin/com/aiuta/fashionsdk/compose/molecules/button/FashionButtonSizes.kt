package com.aiuta.fashionsdk.compose.molecules.button

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.aiuta.fashionsdk.compose.molecules.button.internal.DefaultFashionButtonSize
import com.aiuta.fashionsdk.compose.molecules.button.internal.FashionButtonSize

@Immutable
public object FashionButtonSizes {
    private val IconSize = 14.dp
    private val IconSpacing = 8.dp

    @Composable
    public fun xlSize(
        verticalPadding: Dp = 16.dp,
        horizontalPadding: Dp = 16.dp,
        iconSize: Dp = IconSize,
        iconSpacing: Dp = IconSpacing,
        shape: Shape = RoundedCornerShape(16.dp),
        textStyle: TextStyle = MaterialTheme.typography.body1,
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
    public fun lSize(
        verticalPadding: Dp = 13.dp,
        horizontalPadding: Dp = 16.dp,
        iconSize: Dp = IconSize,
        iconSpacing: Dp = IconSpacing,
        shape: Shape = RoundedCornerShape(12.dp),
        textStyle: TextStyle = MaterialTheme.typography.body2,
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
        verticalPadding: Dp = 9.dp,
        horizontalPadding: Dp = 16.dp,
        iconSize: Dp = IconSize,
        iconSpacing: Dp = IconSpacing,
        shape: Shape = RoundedCornerShape(8.dp),
        textStyle: TextStyle = MaterialTheme.typography.body2,
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
    public fun sSize(
        verticalPadding: Dp = 9.dp,
        horizontalPadding: Dp = 16.dp,
        iconSize: Dp = IconSize,
        iconSpacing: Dp = IconSpacing,
        shape: Shape = RoundedCornerShape(18.dp),
        textStyle: TextStyle = MaterialTheme.typography.body2,
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
    public fun xsSize(
        verticalPadding: Dp = 9.dp,
        horizontalPadding: Dp = 9.dp,
        iconSize: Dp = IconSize,
        iconSpacing: Dp = IconSpacing,
        shape: Shape = CircleShape,
        textStyle: TextStyle = MaterialTheme.typography.body2,
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
