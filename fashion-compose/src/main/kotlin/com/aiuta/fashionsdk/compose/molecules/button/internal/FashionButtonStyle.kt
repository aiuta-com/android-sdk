package com.aiuta.fashionsdk.compose.molecules.button.internal

import androidx.compose.foundation.BorderStroke
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Immutable

@Immutable
public interface FashionButtonStyle {
    public val colors: FashionButtonColor
    public val border: BorderStroke?
}

internal data class DefaultFashionButtonStyle(
    override val colors: DefaultFashionButtonColor,
    override val border: BorderStroke? = null,
) : FashionButtonStyle

internal data class OutlineFashionButtonStyle(
    override val colors: OutlineFashionButtonColor,
    override val border: BorderStroke? =
        BorderStroke(
            ButtonDefaults.OutlinedBorderSize,
            colors.borderColor,
        ),
) : FashionButtonStyle
