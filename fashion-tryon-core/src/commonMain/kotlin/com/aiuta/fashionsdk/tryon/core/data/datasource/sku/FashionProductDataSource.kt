package com.aiuta.fashionsdk.tryon.core.data.datasource.sku

import com.aiuta.fashionsdk.network.paging.models.PageContainer
import com.aiuta.fashionsdk.network.paging.models.PaginationOffset
import com.aiuta.fashionsdk.tryon.core.data.datasource.sku.models.ProductCatalogDTO
import com.aiuta.fashionsdk.tryon.core.data.datasource.sku.models.ProductItemDTO

internal interface FashionProductDataSource {
    // SKU catalogs
    suspend fun getProductCatalogs(
        paginationOffset: PaginationOffset? = null,
        paginationLimit: Int? = null,
    ): PageContainer<ProductCatalogDTO>

    suspend fun getProductItems(
        productCatalogName: String,
        paginationOffset: PaginationOffset? = null,
        paginationLimit: Int? = null,
    ): PageContainer<ProductItemDTO>

    suspend fun getProductItem(
        productCatalogName: String,
        productId: String,
    ): ProductItemDTO
}
