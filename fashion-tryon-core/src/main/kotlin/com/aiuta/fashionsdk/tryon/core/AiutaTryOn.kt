package com.aiuta.fashionsdk.tryon.core

import com.aiuta.fashionsdk.Aiuta
import com.aiuta.fashionsdk.network.exceptions.FashionIOException
import com.aiuta.fashionsdk.network.exceptions.FashionNetworkIsDisconnected
import com.aiuta.fashionsdk.network.paging.models.PageContainer
import com.aiuta.fashionsdk.network.paging.models.PaginationOffset
import com.aiuta.fashionsdk.tryon.core.domain.AiutaTryOnImpl
import com.aiuta.fashionsdk.tryon.core.domain.models.SKUGenerationContainer
import com.aiuta.fashionsdk.tryon.core.domain.models.SKUGenerationItem
import com.aiuta.fashionsdk.tryon.core.domain.models.SKUGenerationStatus
import com.aiuta.fashionsdk.tryon.core.exceptions.FashionReadBytesException
import java.io.FileNotFoundException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

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
     * Flow of current sku generation status
     *
     * @see SKUGenerationStatus for possible variants of status
     */
    @Deprecated("Don't use it, implement by your own with startGeneration")
    public val skuGenerationStatus: StateFlow<SKUGenerationStatus>

    /**
     * Get new page of [SKUGenerationItem]
     *
     * @param catalogName Name of catalog for concrete SKU
     * @param paginationOffset Offset for new page request
     * @param paginationLimit Limit for items in page
     */
    public suspend fun getSKUItems(
        catalogName: String = DEFAULT_CATALOG_NAME,
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
     * @throws FileNotFoundException if the provided URI could not be opened.
     * @throws IllegalStateException if such operation already exist or it's not valid
     * @throws FashionIOException
     * @throws FashionNetworkIsDisconnected
     */
    public fun startSKUGeneration(container: SKUGenerationContainer): Flow<SKUGenerationStatus>

    public companion object {
        public const val DEFAULT_CATALOG_NAME: String = "aiuta_demo"
    }
}
