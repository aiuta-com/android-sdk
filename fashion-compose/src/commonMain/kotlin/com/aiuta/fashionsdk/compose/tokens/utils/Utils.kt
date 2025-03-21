package com.aiuta.fashionsdk.compose.tokens.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.ui.Modifier

private val sharedInteractionSource = MutableInteractionSource()

public fun Modifier.conditional(
    condition: Boolean,
    modifier: Modifier.() -> Modifier,
): Modifier {
    return if (condition) {
        then(modifier(Modifier))
    } else {
        this
    }
}

public fun Modifier.clickableUnindicated(
    enabled: Boolean = true,
    onClick: () -> Unit,
): Modifier {
    return this then
        Modifier.clickable(
            interactionSource = sharedInteractionSource,
            indication = null,
            enabled = enabled,
            onClickLabel = null,
            role = null,
            onClick,
        )
}
