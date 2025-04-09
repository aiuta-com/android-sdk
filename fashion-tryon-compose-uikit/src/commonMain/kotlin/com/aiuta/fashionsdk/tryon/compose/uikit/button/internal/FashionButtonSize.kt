package com.aiuta.fashionsdk.tryon.compose.uikit.button.internal

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp

@Immutable
public interface FashionButtonSize {
    public val paddingValues: PaddingValues
    public val iconSize: Dp
    public val iconSpacing: Dp
    public val shape: Shape
    public val textStyle: TextStyle
}

internal data class DefaultFashionButtonSize(
    override val paddingValues: PaddingValues,
    override val iconSize: Dp,
    override val iconSpacing: Dp,
    override val shape: Shape,
    override val textStyle: TextStyle,
) : FashionButtonSize
