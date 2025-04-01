package com.aiuta.fashionsdk.tryon.compose.ui.internal.components.block

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import com.aiuta.fashionsdk.compose.tokens.composition.LocalTheme
import com.aiuta.fashionsdk.tryon.compose.configuration.models.product.SKUItem

@Composable
internal fun solveGeneralPriceColor(activeSKUItem: SKUItem): Color {
    val theme = LocalTheme.current

    return if (activeSKUItem.localizedOldPrice?.isNotBlank() == true) {
        theme.colors.secondary
    } else {
        theme.colors.primary
    }
}

internal fun solveGeneralPriceDecoration(activeSKUItem: SKUItem): TextDecoration? = if (activeSKUItem.localizedOldPrice?.isNotBlank() == true) {
    TextDecoration.LineThrough
} else {
    null
}
