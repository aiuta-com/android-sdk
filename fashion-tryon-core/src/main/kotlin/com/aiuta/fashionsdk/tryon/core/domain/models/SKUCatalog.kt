package com.aiuta.fashionsdk.tryon.core.domain.models

import com.aiuta.fashionsdk.tryon.core.data.datasource.sku.models.SKUCatalogDTO

public class SKUCatalog(
    public val id: String,
    public val skuCatalogName: String,
)

internal fun SKUCatalogDTO.toPublic(): SKUCatalog {
    return SKUCatalog(
        id = id,
        skuCatalogName = skuCatalogName,
    )
}
