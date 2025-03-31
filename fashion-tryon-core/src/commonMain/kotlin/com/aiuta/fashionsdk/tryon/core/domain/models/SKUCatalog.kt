package com.aiuta.fashionsdk.tryon.core.domain.models

import com.aiuta.fashionsdk.tryon.core.data.datasource.sku.models.SKUCatalogDTO

/**
 * Catalog of sku item for next generations
 *
 * @param id - Id of catalog
 * @param catalogName - Name of catalog
 */
public class SKUCatalog(
    public val id: String,
    public val catalogName: String,
)

internal fun SKUCatalogDTO.toPublic(): SKUCatalog {
    return SKUCatalog(
        id = id,
        catalogName = catalogName,
    )
}
