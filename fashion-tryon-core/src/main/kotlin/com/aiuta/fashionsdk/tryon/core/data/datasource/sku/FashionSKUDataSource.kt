package com.aiuta.fashionsdk.tryon.core.data.datasource.sku

import com.aiuta.fashionsdk.network.paging.models.PageContainer
import com.aiuta.fashionsdk.network.paging.models.PaginationOffset
import com.aiuta.fashionsdk.tryon.core.data.datasource.sku.models.CreateSKUItemRequest
import com.aiuta.fashionsdk.tryon.core.data.datasource.sku.models.SKUCatalogDTO
import com.aiuta.fashionsdk.tryon.core.data.datasource.sku.models.SKUItemDTO

internal interface FashionSKUDataSource {
    // SKU catalogs
    suspend fun getSKUCatalogs(
        paginationOffset: PaginationOffset? = null,
        paginationLimit: Int? = null,
    ): PageContainer<SKUCatalogDTO>

    // SKU items
    suspend fun createSKUItem(
        skuCatalogName: String,
        request: CreateSKUItemRequest,
    ): SKUItemDTO

    suspend fun getSKUItems(
        skuCatalogName: String,
        paginationOffset: PaginationOffset? = null,
        paginationLimit: Int? = null,
    ): PageContainer<SKUItemDTO>

    suspend fun getSKUItem(
        skuCatalogName: String,
        skuId: String,
    ): SKUItemDTO

    suspend fun deleteSKUItem(
        skuCatalogName: String,
        skuId: String,
    ): SKUItemDTO
}
