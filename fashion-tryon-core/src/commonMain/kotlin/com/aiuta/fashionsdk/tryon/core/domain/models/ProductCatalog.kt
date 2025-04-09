package com.aiuta.fashionsdk.tryon.core.domain.models

import com.aiuta.fashionsdk.tryon.core.data.datasource.sku.models.ProductCatalogDTO

/**
 * Catalog of product item for next generations
 *
 * @param id - Id of catalog
 * @param catalogName - Name of catalog
 */
public class ProductCatalog(
    public val id: String,
    public val catalogName: String,
)

internal fun ProductCatalogDTO.toPublic(): ProductCatalog = ProductCatalog(
    id = id,
    catalogName = catalogName,
)
