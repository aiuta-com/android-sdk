package com.aiuta.fashionsdk.tryon.compose.configuration.features.tryon.dataprovider

import com.aiuta.fashionsdk.tryon.compose.configuration.models.product.ProductItem

public interface AiutaTryOnFeatureDataProvider {
    public val addToCartClick: (productItem: ProductItem) -> Unit
}
