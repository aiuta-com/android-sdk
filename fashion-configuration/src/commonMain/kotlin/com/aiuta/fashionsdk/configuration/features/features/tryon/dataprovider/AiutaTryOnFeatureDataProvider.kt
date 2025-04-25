package com.aiuta.fashionsdk.configuration.features.features.tryon.dataprovider

import com.aiuta.fashionsdk.configuration.features.models.product.ProductItem

public interface AiutaTryOnFeatureDataProvider {
    public val addToCartClick: (productItem: ProductItem) -> Unit
}
