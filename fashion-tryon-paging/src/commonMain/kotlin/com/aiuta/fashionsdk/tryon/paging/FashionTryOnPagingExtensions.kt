package com.aiuta.fashionsdk.tryon.paging

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.aiuta.fashionsdk.network.paging.ContainerPagingSource
import com.aiuta.fashionsdk.tryon.core.AiutaTryOn
import com.aiuta.fashionsdk.tryon.core.domain.models.SKUCatalog
import com.aiuta.fashionsdk.tryon.core.domain.models.SKUGenerationItem
import kotlinx.coroutines.flow.Flow

/**
 * Extension for using [getSKUItems] method with [androidx.paging] library
 */
public fun AiutaTryOn.getSKUItems(catalogName: String): Flow<PagingData<SKUGenerationItem>> = Pager(
    config =
    PagingConfig(
        pageSize = ContainerPagingSource.DEFAULT_PAGE_SIZE,
        enablePlaceholders = false,
    ),
    pagingSourceFactory = {
        ContainerPagingSource {
            getSKUItems(
                catalogName = catalogName,
                paginationOffset = it,
            )
        }
    },
).flow

/**
 * Extension for using [getSKUCatalogs] method with [androidx.paging] library
 */
public fun AiutaTryOn.getSKUCatalogs(): Flow<PagingData<SKUCatalog>> = Pager(
    config =
    PagingConfig(
        pageSize = ContainerPagingSource.DEFAULT_PAGE_SIZE,
        enablePlaceholders = false,
    ),
    pagingSourceFactory = {
        ContainerPagingSource {
            getSKUCatalogs(
                paginationOffset = it,
            )
        }
    },
).flow
