package com.aiuta.fashionsdk.tryon.core.domain.models

import com.aiuta.fashionsdk.tryon.core.data.datasource.sku.models.SKUItemDTO

/**
 * One of possible item for generation SKU flow
 *
 * @param skuId - Id of sku to generate
 * @param catalogName - Name of catalog with current [skuId]
 */
public data class SKUGenerationItem(
    val skuId: String,
    val catalogName: String? = null,
)

internal fun SKUItemDTO.toPublic(): SKUGenerationItem {
    return SKUGenerationItem(
        skuId = skuId,
        catalogName = skuCatalogName,
    )
}
