package com.aiuta.fashionsdk.tryon.compose.ui.internal.components.block

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import com.aiuta.fashionsdk.compose.tokens.FashionColor
import com.aiuta.fashionsdk.tryon.compose.domain.models.SKUItem

internal fun solveGeneralPriceColor(activeSKUItem: SKUItem): Color {
    return if (activeSKUItem.priceDiscounted != null) {
        FashionColor.Gray
    } else {
        FashionColor.Black
    }
}

internal fun solveGeneralPriceDecoration(activeSKUItem: SKUItem): TextDecoration? {
    return activeSKUItem.priceDiscounted?.let {
        TextDecoration.LineThrough
    }
}
