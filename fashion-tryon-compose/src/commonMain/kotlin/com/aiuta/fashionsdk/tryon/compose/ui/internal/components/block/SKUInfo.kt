package com.aiuta.fashionsdk.tryon.compose.ui.internal.components.block

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aiuta.fashionsdk.compose.tokens.composition.LocalTheme
import com.aiuta.fashionsdk.tryon.compose.configuration.models.product.SKUItem

@Composable
internal fun SKUInfo(
    modifier: Modifier = Modifier,
    skuItem: SKUItem,
) {
    val theme = LocalTheme.current

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = skuItem.store,
            style = theme.typography.brandName,
            color = theme.colors.primary,
        )

        Spacer(Modifier.height(2.dp))

        Text(
            text = skuItem.description,
            style = theme.typography.productName,
            color = theme.colors.primary,
        )

        Spacer(Modifier.height(4.dp))

        PriceInfo(
            modifier = Modifier.fillMaxWidth(),
            skuItem = skuItem,
        )
    }
}
