package com.aiuta.fashionsdk.tryon.core.domain

import com.aiuta.fashionsdk.Aiuta
import com.aiuta.fashionsdk.analytic.InternalAiutaAnalytic
import com.aiuta.fashionsdk.analytic.internalAiutaAnalytic
import com.aiuta.fashionsdk.analytic.model.TryOnError
import com.aiuta.fashionsdk.network.paging.models.PageContainer
import com.aiuta.fashionsdk.network.paging.models.PaginationOffset
import com.aiuta.fashionsdk.tryon.core.AiutaTryOn
import com.aiuta.fashionsdk.tryon.core.data.datasource.operation.FashionSKUOperationsDataSource
import com.aiuta.fashionsdk.tryon.core.data.datasource.operation.models.CreateSKUOperationRequest
import com.aiuta.fashionsdk.tryon.core.data.datasource.operation.skuOperationsDataSourceFactory
import com.aiuta.fashionsdk.tryon.core.data.datasource.sku.FashionSKUDataSource
import com.aiuta.fashionsdk.tryon.core.data.datasource.sku.skuDataSourceFactory
import com.aiuta.fashionsdk.tryon.core.domain.analytic.sendFinishTryOnEvent
import com.aiuta.fashionsdk.tryon.core.domain.analytic.sendStartTryOnEvent
import com.aiuta.fashionsdk.tryon.core.domain.analytic.sendTryOnErrorEvent
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
import kotlin.system.measureTimeMillis
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

internal class AiutaTryOnImpl(
    private val analytic: InternalAiutaAnalytic,
    private val pingOperationSlice: PingOperationSlice,
    private val uploadImageSlice: UploadImageSlice,
    private val skuDataSource: FashionSKUDataSource,
    private val skuOperationsDataSource: FashionSKUOperationsDataSource,
) : AiutaTryOn {
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
            measureTryOn(container) {
                errorListener {
                    // Set loading state with previous image urls
                    _skuGenerationStatus.emit(
                        SKUGenerationStatus.LoadingGenerationStatus(),
                    )

                    // Firstly, upload image on backend
                    val uploadedImage =
                        trackException(TryOnError.Type.UPLOAD_FAILED) {
                            uploadImageSlice.uploadImage(
                                fileUri = container.fileUri,
                                fileName = generateFileName(),
                            )
                        }

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
                    trackException(TryOnError.Type.TRY_ON_START_FAILED) {
                        pingOperationSlice.startOperationTypeListening(
                            operationId = newOperation.operationId,
                        )
                    }

                    trackException(TryOnError.Type.TRY_ON_OPERATION_FAILED) {
                        pingOperationSlice
                            .getPingGenerationStatusFlow(operationId = newOperation.operationId)
                            ?.first { it.isTerminate() }
                            .also { solveTerminatedOperationResult(it) }
                    }
                }
            }
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

    private suspend fun errorListener(action: suspend () -> Unit) {
        try {
            action()
        } catch (e: Exception) {
            // Fallback with error
            _skuGenerationStatus.emit(
                SKUGenerationStatus.ErrorGenerationStatus(
                    imageUrls = _skuGenerationStatus.value.imageUrls,
                ),
            )
            throw e
        }
    }

    private suspend fun measureTryOn(
        container: SKUGenerationContainer,
        action: suspend () -> Unit,
    ) {
        analytic.sendStartTryOnEvent(container)
        val loadingTimeMillis = measureTimeMillis { action() }
        analytic.sendFinishTryOnEvent(container, loadingTimeMillis)
    }

    private suspend fun <T> trackException(
        type: TryOnError.Type,
        action: suspend () -> T,
    ): T {
        return try {
            action()
        } catch (e: Exception) {
            // Logging exception
            analytic.sendTryOnErrorEvent(type)
            throw e
        }
    }

    companion object {
        fun create(aiuta: Aiuta): AiutaTryOn {
            return AiutaTryOnImpl(
                analytic = aiuta.internalAiutaAnalytic,
                pingOperationSlice = aiuta.pingOperationSliceFactory,
                uploadImageSlice = aiuta.uploadImageSliceFactory,
                skuDataSource = aiuta.skuDataSourceFactory,
                skuOperationsDataSource = aiuta.skuOperationsDataSourceFactory,
            )
        }
    }
}
