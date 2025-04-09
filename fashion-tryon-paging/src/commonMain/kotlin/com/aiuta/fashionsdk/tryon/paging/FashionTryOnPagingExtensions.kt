package com.aiuta.fashionsdk.tryon.paging

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.aiuta.fashionsdk.network.paging.ContainerPagingSource
import com.aiuta.fashionsdk.tryon.core.AiutaTryOn
import com.aiuta.fashionsdk.tryon.core.domain.models.ProductCatalog
import com.aiuta.fashionsdk.tryon.core.domain.models.ProductGenerationItem
import kotlinx.coroutines.flow.Flow

/**
 * Extension for using [getProductItems] method with [androidx.paging] library
 */
public fun AiutaTryOn.getProductItems(catalogName: String): Flow<PagingData<ProductGenerationItem>> = Pager(
    config =
    PagingConfig(
        pageSize = ContainerPagingSource.DEFAULT_PAGE_SIZE,
        enablePlaceholders = false,
    ),
    pagingSourceFactory = {
        ContainerPagingSource {
            getProductItems(
                catalogName = catalogName,
                paginationOffset = it,
            )
        }
    },
).flow

/**
 * Extension for using [getProductCatalogs] method with [androidx.paging] library
 */
public fun AiutaTryOn.getProductCatalogs(): Flow<PagingData<ProductCatalog>> = Pager(
    config =
    PagingConfig(
        pageSize = ContainerPagingSource.DEFAULT_PAGE_SIZE,
        enablePlaceholders = false,
    ),
    pagingSourceFactory = {
        ContainerPagingSource {
            getProductCatalogs(
                paginationOffset = it,
            )
        }
    },
).flow
