package com.aiuta.fashionsdk.tryon.compose.ui.internal.components.block

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.aiuta.fashionsdk.tryon.compose.configuration.models.product.SKUItem
import com.aiuta.fashionsdk.tryon.compose.uikit.composition.LocalTheme

@Composable
internal fun PriceInfo(
    modifier: Modifier = Modifier,
    skuItem: SKUItem,
) {
    val theme = LocalTheme.current

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        val localizedOldPrice = skuItem.localizedOldPrice

        if (localizedOldPrice?.isNotBlank() == true) {
            Text(
                text = localizedOldPrice,
                style = theme.productBar.typography.price,
                color = theme.productBar.colors.discountedPrice,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )

            Spacer(Modifier.width(4.dp))
        }

        if (skuItem.localizedPrice.isNotBlank()) {
            Text(
                text = skuItem.localizedPrice,
                style = theme.productBar.typography.price.copy(
                    textDecoration = solveGeneralPriceDecoration(skuItem),
                ),
                color = solveGeneralPriceColor(skuItem),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}
