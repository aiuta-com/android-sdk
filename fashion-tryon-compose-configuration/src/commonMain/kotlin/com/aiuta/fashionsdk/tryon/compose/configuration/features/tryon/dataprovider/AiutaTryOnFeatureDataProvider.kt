package com.aiuta.fashionsdk.tryon.compose.configuration.features.tryon.dataprovider

import com.aiuta.fashionsdk.tryon.compose.configuration.models.product.SKUItem

public class AiutaTryOnFeatureDataProvider(
    public val addToCartClick: (skuItem: SKUItem) -> Unit,
)
