package com.aiuta.fashionsdk.tryon.core.data.datasource.sku.internal

import com.aiuta.fashionsdk.network.NetworkClient
import com.aiuta.fashionsdk.network.paging.models.PageContainer
import com.aiuta.fashionsdk.network.paging.models.PaginationOffset
import com.aiuta.fashionsdk.network.paging.utils.saveAppend
import com.aiuta.fashionsdk.network.utils.saveAppendLimit
import com.aiuta.fashionsdk.tryon.core.data.datasource.sku.FashionSKUDataSource
import com.aiuta.fashionsdk.tryon.core.data.datasource.sku.models.SKUCatalogDTO
import com.aiuta.fashionsdk.tryon.core.data.datasource.sku.models.SKUItemDTO
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

internal class FashionSKURemoteDataSource(
    private val networkClient: NetworkClient,
) : FashionSKUDataSource {
    // SKU catalogs
    override suspend fun getSKUCatalogs(
        paginationOffset: PaginationOffset?,
        paginationLimit: Int?,
    ): PageContainer<SKUCatalogDTO> {
        return withContext(Dispatchers.IO) {
            networkClient.httpClient.value.get(
                urlString = PATH_SKU_CATALOGS,
            ) {
                url {
                    saveAppend(paginationOffset)
                    saveAppendLimit(paginationLimit)
                }
            }.body()
        }
    }

    override suspend fun getSKUItems(
        skuCatalogName: String,
        paginationOffset: PaginationOffset?,
        paginationLimit: Int?,
    ): PageContainer<SKUItemDTO> {
        return withContext(Dispatchers.IO) {
            networkClient.httpClient.value.get(
                urlString = "$PATH_SKU_ITEMS/$skuCatalogName",
            ) {
                url {
                    saveAppend(paginationOffset)
                    saveAppendLimit(paginationLimit)
                }
            }.body()
        }
    }

    override suspend fun getSKUItem(
        skuCatalogName: String,
        skuId: String,
    ): SKUItemDTO {
        return withContext(Dispatchers.IO) {
            networkClient.httpClient.value.get(
                urlString = "$PATH_SKU_ITEMS/$skuCatalogName/$skuId",
            ).body()
        }
    }

    private companion object {
        const val PATH_SKU_ITEMS = "/sku_items"
        const val PATH_SKU_CATALOGS = "/sku_catalogs"
    }
}
