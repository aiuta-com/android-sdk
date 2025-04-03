package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.components.footer.blocks.description

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.aiuta.fashionsdk.compose.molecules.button.FashionButton
import com.aiuta.fashionsdk.compose.molecules.button.FashionButtonSizes
import com.aiuta.fashionsdk.compose.molecules.button.FashionButtonStyles
import com.aiuta.fashionsdk.compose.tokens.composition.LocalTheme
import com.aiuta.fashionsdk.internal.analytic.model.AiutaAnalyticPageId
import com.aiuta.fashionsdk.tryon.compose.configuration.features.tryon.AiutaTryOnFeature
import com.aiuta.fashionsdk.tryon.compose.ui.internal.analytic.clickAddToCart
import com.aiuta.fashionsdk.tryon.compose.ui.internal.components.block.SKUInfo
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.components.footer.FOOTER_FULL_SIZE_SPAN
import com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.features.strictProvideFeature

internal fun LazyGridScope.itemDescriptionBlock(modifier: Modifier = Modifier) {
    item(
        key = "ITEM_DESCRIPTION_BLOCK_KEY",
        span = { GridItemSpan(FOOTER_FULL_SIZE_SPAN) },
        contentType = "ITEM_DESCRIPTION_BLOCK_TYPE",
    ) {
        ItemDescriptionBlock(
            modifier = modifier,
        )
    }
}

@Composable
internal fun ItemDescriptionBlock(modifier: Modifier = Modifier) {
    val controller = LocalController.current
    val theme = LocalTheme.current

    val activeSKUItem = controller.activeSKUItem.value

    val tryOnFeature = strictProvideFeature<AiutaTryOnFeature>()

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.Top,
    ) {
        SKUInfo(
            modifier = Modifier.weight(1f),
            skuItem = activeSKUItem,
        )

        tryOnFeature.dataProvider?.let { dataProvider ->
            Spacer(Modifier.width(16.dp))

            FashionButton(
                text = tryOnFeature.strings.tryOnButtonAddToCart,
                style = FashionButtonStyles.primaryStyle(theme),
                size = FashionButtonSizes.lSize(horizontalPadding = 30.dp),
                onClick = {
                    controller.clickAddToCart(
                        pageId = AiutaAnalyticPageId.RESULTS,
                        skuId = activeSKUItem.skuId,
                        dataProvider = dataProvider,
                    )
                },
            )
        }
    }
}
