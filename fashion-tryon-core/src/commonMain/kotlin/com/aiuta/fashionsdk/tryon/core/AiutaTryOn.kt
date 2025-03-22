package com.aiuta.fashionsdk.tryon.core

import com.aiuta.fashionsdk.Aiuta
import com.aiuta.fashionsdk.network.exceptions.FashionIOException
import com.aiuta.fashionsdk.network.exceptions.FashionNetworkIsDisconnected
import com.aiuta.fashionsdk.network.paging.models.PageContainer
import com.aiuta.fashionsdk.network.paging.models.PaginationOffset
import com.aiuta.fashionsdk.tryon.core.domain.AiutaTryOnImpl
import com.aiuta.fashionsdk.tryon.core.domain.models.SKUCatalog
import com.aiuta.fashionsdk.tryon.core.domain.models.SKUGenerationContainer
import com.aiuta.fashionsdk.tryon.core.domain.models.SKUGenerationItem
import com.aiuta.fashionsdk.tryon.core.domain.models.SKUGenerationStatus
import com.aiuta.fashionsdk.tryon.core.exceptions.FashionReadBytesException
import kotlinx.coroutines.flow.Flow

/**
 * Default Kotlin extension for creating [AiutaTryOn]
 *
 * @return new instance of [AiutaTryOn]
 */
public val Aiuta.tryon: AiutaTryOn
    get() = AiutaTryOnImpl.create(aiuta = this)

/**
 * Entry point for all functionality of Digital try on
 */
public interface AiutaTryOn {
    /**
     * Get new page of [SKUCatalog]
     *
     * @param paginationOffset Offset for new page request
     * @param paginationLimit Limit for items in page
     */
    public suspend fun getSKUCatalogs(
        paginationOffset: PaginationOffset? = null,
        paginationLimit: Int? = null,
    ): PageContainer<SKUCatalog>

    /**
     * Get new page of [SKUGenerationItem]
     *
     * @param catalogName Name of catalog for concrete SKU
     * @param paginationOffset Offset for new page request
     * @param paginationLimit Limit for items in page
     */
    public suspend fun getSKUItems(
        catalogName: String,
        paginationOffset: PaginationOffset? = null,
        paginationLimit: Int? = null,
    ): PageContainer<SKUGenerationItem>

    /**
     * Start sku generation by user image
     *
     * Pipeline is next:
     * - Try to compress image or use origin
     * - Upload image on backend for processing
     * - Create generation operation and wait for result
     *
     * @throws FashionReadBytesException if the provided ByteArray will be empty or null
     * @throws IllegalStateException if such operation already exist or it's not valid
     * @throws FashionIOException
     * @throws FashionNetworkIsDisconnected
     *
     * @return flow with statuses of started generation
     */
    public fun startSKUGeneration(container: SKUGenerationContainer): Flow<SKUGenerationStatus>
}
