package com.aiuta.fashionsdk.tryon.compose.ui.internal.components.block

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import com.aiuta.fashionsdk.tryon.compose.configuration.models.product.ProductItem
import com.aiuta.fashionsdk.tryon.compose.uikit.composition.LocalTheme

@Composable
internal fun solveGeneralPriceColor(activeProductItem: ProductItem): Color {
    val theme = LocalTheme.current

    return if (activeProductItem.localizedOldPrice?.isNotBlank() == true) {
        theme.color.secondary
    } else {
        theme.color.primary
    }
}

internal fun solveGeneralPriceDecoration(activeProductItem: ProductItem): TextDecoration? = if (activeProductItem.localizedOldPrice?.isNotBlank() == true) {
    TextDecoration.LineThrough
} else {
    null
}
