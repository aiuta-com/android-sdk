package com.aiuta.fashionsdk.tryon.core.domain

import com.aiuta.fashionsdk.Fashion
import com.aiuta.fashionsdk.network.paging.models.PageContainer
import com.aiuta.fashionsdk.network.paging.models.PaginationOffset
import com.aiuta.fashionsdk.tryon.core.FashionTryOn
import com.aiuta.fashionsdk.tryon.core.data.datasource.operation.FashionSKUOperationsDataSource
import com.aiuta.fashionsdk.tryon.core.data.datasource.operation.models.CreateSKUOperationRequest
import com.aiuta.fashionsdk.tryon.core.data.datasource.operation.skuOperationsDataSourceFactory
import com.aiuta.fashionsdk.tryon.core.data.datasource.sku.FashionSKUDataSource
import com.aiuta.fashionsdk.tryon.core.data.datasource.sku.skuDataSourceFactory
import com.aiuta.fashionsdk.tryon.core.domain.models.PingGenerationStatus
import com.aiuta.fashionsdk.tryon.core.domain.models.SKUGenerationContainer
import com.aiuta.fashionsdk.tryon.core.domain.models.SKUGenerationItem
import com.aiuta.fashionsdk.tryon.core.domain.models.SKUGenerationStatus
import com.aiuta.fashionsdk.tryon.core.domain.models.isTerminate
import com.aiuta.fashionsdk.tryon.core.domain.models.toPublic
import com.aiuta.fashionsdk.tryon.core.domain.slice.ping.PingOperationSlice
import com.aiuta.fashionsdk.tryon.core.domain.slice.ping.pingOperationSliceFactory
import com.aiuta.fashionsdk.tryon.core.domain.slice.upload.UploadImageSlice
import com.aiuta.fashionsdk.tryon.core.domain.slice.upload.uploadImageSliceFactory
import com.aiuta.fashionsdk.tryon.core.utils.generateFileName
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

internal class FashionTryOnImpl(
    private val pingOperationSlice: PingOperationSlice,
    private val uploadImageSlice: UploadImageSlice,
    private val skuDataSource: FashionSKUDataSource,
    private val skuOperationsDataSource: FashionSKUOperationsDataSource,
) : FashionTryOn {
    private val mutex = Mutex()

    private val _skuGenerationStatus: MutableStateFlow<SKUGenerationStatus> =
        MutableStateFlow(SKUGenerationStatus.NothingGenerateStatus)
    override val skuGenerationStatus: StateFlow<SKUGenerationStatus> = _skuGenerationStatus

    override suspend fun getSKUItems(
        catalogName: String,
        paginationOffset: PaginationOffset?,
        paginationLimit: Int?,
    ): PageContainer<SKUGenerationItem> {
        val skuDTOs =
            skuDataSource.getSKUItems(
                skuCatalogName = catalogName,
                paginationOffset = paginationOffset,
                paginationLimit = paginationLimit,
            )

        return PageContainer(
            result = skuDTOs.result.map { it.toPublic() },
            beforeKey = skuDTOs.beforeKey,
            afterKey = skuDTOs.afterKey,
            errors = skuDTOs.errors,
        )
    }

    override suspend fun startSKUGeneration(containers: List<SKUGenerationContainer>) {
        containers.forEach { container ->
            startSKUGeneration(
                container = container,
            )
        }
    }

    override suspend fun startSKUGeneration(container: SKUGenerationContainer) {
        mutex.withLock {
            // Set loading state with previous image urls
            _skuGenerationStatus.emit(
                SKUGenerationStatus.LoadingGenerationStatus(),
            )

            // Firstly, upload image on backend
            val uploadedImage =
                uploadImageSlice.uploadImage(
                    fileUri = container.fileUri,
                    fileName = generateFileName(),
                )

            // Secondly, create sku generation operation
            val newOperation =
                skuOperationsDataSource.createSKUOperation(
                    request =
                        CreateSKUOperationRequest(
                            skuCatalogName = container.skuCatalogName,
                            skuId = container.skuId,
                            uploadedImageId = uploadedImage.id,
                        ),
                )

            // Finally, wait for the operation, until it is completed
            pingOperationSlice.startOperationTypeListening(
                operationId = newOperation.operationId,
            )
            pingOperationSlice
                .getPingGenerationStatusFlow(operationId = newOperation.operationId)
                ?.first { it.isTerminate() }
                .also { solveTerminatedOperationResult(it) }
        }
    }

    private suspend fun solveTerminatedOperationResult(terminatedOperation: PingGenerationStatus?) {
        // Check, if last operation is finished and we got it
        requireNotNull(terminatedOperation)

        // Solve, what to emit outside
        when (terminatedOperation) {
            is PingGenerationStatus.SuccessPingGenerationStatus -> {
                // Add new image urls to current generation
                _skuGenerationStatus.emit(
                    SKUGenerationStatus.SuccessGenerationStatus(
                        imageUrls = _skuGenerationStatus.value.imageUrls + terminatedOperation.imageUrls,
                    ),
                )
            }

            else -> {
                // Fallback with last success generation result
                _skuGenerationStatus.emit(
                    SKUGenerationStatus.ErrorGenerationStatus(
                        imageUrls = _skuGenerationStatus.value.imageUrls,
                    ),
                )
            }
        }
    }

    companion object {
        fun create(fashion: Fashion): FashionTryOn {
            return FashionTryOnImpl(
                pingOperationSlice = fashion.pingOperationSliceFactory,
                uploadImageSlice = fashion.uploadImageSliceFactory,
                skuDataSource = fashion.skuDataSourceFactory,
                skuOperationsDataSource = fashion.skuOperationsDataSourceFactory,
            )
        }
    }
}
