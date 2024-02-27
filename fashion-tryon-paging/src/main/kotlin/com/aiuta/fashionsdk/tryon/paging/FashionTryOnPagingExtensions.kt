package com.aiuta.fashionsdk.tryon.paging

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.aiuta.fashionsdk.network.paging.ContainerPagingSource
import com.aiuta.fashionsdk.tryon.core.FashionTryOn
import com.aiuta.fashionsdk.tryon.core.domain.models.SKUGenerationItem
import kotlinx.coroutines.flow.Flow

/**
 * Extension for using [getSKUItems] method with [androidx.paging] library
 */
public fun FashionTryOn.getSKUItems(
    catalogName: String = FashionTryOn.DEFAULT_CATALOG_NAME,
): Flow<PagingData<SKUGenerationItem>> {
    return Pager(
        config =
            PagingConfig(
                pageSize = ContainerPagingSource.DEFAULT_PAGE_SIZE,
                enablePlaceholders = false,
            ),
        pagingSourceFactory = {
            ContainerPagingSource {
                getSKUItems(catalogName = catalogName)
            }
        },
    ).flow
}
