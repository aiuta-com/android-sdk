package com.aiuta.fashionsdk.tryon.compose.ui.internal.components.block

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import com.aiuta.fashionsdk.tryon.compose.domain.models.SKUItem
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.LocalTheme

@Composable
internal fun solveGeneralPriceColor(activeSKUItem: SKUItem): Color {
    val theme = LocalTheme.current

    return if (activeSKUItem.priceDiscounted != null) {
        theme.colors.secondary
    } else {
        theme.colors.primary
    }
}

internal fun solveGeneralPriceDecoration(activeSKUItem: SKUItem): TextDecoration? {
    return activeSKUItem.priceDiscounted?.let {
        TextDecoration.LineThrough
    }
}
