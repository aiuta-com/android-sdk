package com.aiuta.fashionsdk.tryon.core.domain

import com.aiuta.fashionsdk.Aiuta
import com.aiuta.fashionsdk.analytic.InternalAiutaAnalytic
import com.aiuta.fashionsdk.analytic.internalAiutaAnalytic
import com.aiuta.fashionsdk.analytic.model.TryOnError
import com.aiuta.fashionsdk.network.paging.models.PageContainer
import com.aiuta.fashionsdk.network.paging.models.PaginationOffset
import com.aiuta.fashionsdk.tryon.core.AiutaTryOn
import com.aiuta.fashionsdk.tryon.core.data.datasource.image.models.UploadedImage
import com.aiuta.fashionsdk.tryon.core.data.datasource.operation.FashionSKUOperationsDataSource
import com.aiuta.fashionsdk.tryon.core.data.datasource.operation.models.CreateSKUOperationRequest
import com.aiuta.fashionsdk.tryon.core.data.datasource.operation.skuOperationsDataSourceFactory
import com.aiuta.fashionsdk.tryon.core.data.datasource.sku.FashionSKUDataSource
import com.aiuta.fashionsdk.tryon.core.data.datasource.sku.skuDataSourceFactory
import com.aiuta.fashionsdk.tryon.core.domain.models.PingGenerationStatus
import com.aiuta.fashionsdk.tryon.core.domain.models.SKUCatalog
import com.aiuta.fashionsdk.tryon.core.domain.models.SKUGenerationContainer
import com.aiuta.fashionsdk.tryon.core.domain.models.SKUGenerationItem
import com.aiuta.fashionsdk.tryon.core.domain.models.SKUGenerationStatus
import com.aiuta.fashionsdk.tryon.core.domain.models.SKUGenerationUriContainer
import com.aiuta.fashionsdk.tryon.core.domain.models.SKUGenerationUrlContainer
import com.aiuta.fashionsdk.tryon.core.domain.models.isTerminate
import com.aiuta.fashionsdk.tryon.core.domain.models.toPublic
import com.aiuta.fashionsdk.tryon.core.domain.slice.ping.PingOperationSlice
import com.aiuta.fashionsdk.tryon.core.domain.slice.ping.pingOperationSliceFactory
import com.aiuta.fashionsdk.tryon.core.domain.slice.upload.UploadImageSlice
import com.aiuta.fashionsdk.tryon.core.domain.slice.upload.uploadImageSliceFactory
import com.aiuta.fashionsdk.tryon.core.domain.utils.errorListener
import com.aiuta.fashionsdk.tryon.core.domain.utils.measureTryOn
import com.aiuta.fashionsdk.tryon.core.domain.utils.trackException
import com.aiuta.fashionsdk.tryon.core.utils.generateFileName
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

internal class AiutaTryOnImpl(
    private val analytic: InternalAiutaAnalytic,
    private val pingOperationSlice: PingOperationSlice,
    private val uploadImageSlice: UploadImageSlice,
    private val skuDataSource: FashionSKUDataSource,
    private val skuOperationsDataSource: FashionSKUOperationsDataSource,
) : AiutaTryOn {
    override suspend fun getSKUCatalogs(
        paginationOffset: PaginationOffset?,
        paginationLimit: Int?,
    ): PageContainer<SKUCatalog> {
        val skuCatalogs =
            skuDataSource.getSKUCatalogs(
                paginationOffset = paginationOffset,
                paginationLimit,
            )

        return PageContainer(
            result = skuCatalogs.result.map { it.toPublic() },
            beforeKey = skuCatalogs.beforeKey,
            afterKey = skuCatalogs.afterKey,
            errors = skuCatalogs.errors,
        )
    }

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

    override fun startSKUGeneration(container: SKUGenerationContainer): Flow<SKUGenerationStatus> {
        return flow {
            measureTryOn(
                analytic = analytic,
                container = container,
            ) {
                errorListener {
                    // Set loading state with previous image urls
                    emit(SKUGenerationStatus.LoadingGenerationStatus.StartGeneration)

                    // Firstly, upload image on backend
                    val uploadedImage =
                        trackException(
                            analytic = analytic,
                            type = TryOnError.Type.UPLOAD_FAILED,
                        ) {
                            solveUploadingImage(container)
                        }
                    emit(
                        SKUGenerationStatus.LoadingGenerationStatus.UploadedSourceImage(
                            sourceImageId = uploadedImage.id,
                            sourceImageUrl = uploadedImage.url,
                        ),
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
                    emit(
                        SKUGenerationStatus.LoadingGenerationStatus.GenerationProcessing(
                            sourceImageId = uploadedImage.id,
                            sourceImageUrl = uploadedImage.url,
                        ),
                    )
                    trackException(
                        analytic = analytic,
                        type = TryOnError.Type.TRY_ON_START_FAILED,
                    ) {
                        pingOperationSlice.startOperationTypeListening(
                            operationId = newOperation.operationId,
                        )
                    }

                    trackException(
                        analytic = analytic,
                        type = TryOnError.Type.TRY_ON_OPERATION_FAILED,
                    ) {
                        pingOperationSlice
                            .getPingGenerationStatusFlow(operationId = newOperation.operationId)
                            ?.first { it.isTerminate() }
                            .also {
                                solveTerminatedOperationResult(
                                    sourceImageUrl = uploadedImage.url,
                                    terminatedOperation = it,
                                )
                            }
                    }
                }
            }
        }
    }

    private suspend fun solveUploadingImage(container: SKUGenerationContainer): UploadedImage {
        return when (container) {
            is SKUGenerationUriContainer -> {
                uploadImageSlice.uploadImage(
                    fileUri = container.fileUri,
                    fileName = generateFileName(),
                )
            }

            is SKUGenerationUrlContainer -> {
                UploadedImage(
                    id = container.fileId,
                    url = container.fileUrl,
                )
            }
        }
    }

    private suspend fun FlowCollector<SKUGenerationStatus>.solveTerminatedOperationResult(
        sourceImageUrl: String,
        terminatedOperation: PingGenerationStatus?,
    ) {
        // Check, if last operation is finished and we got it
        requireNotNull(terminatedOperation)

        // Solve, what to emit outside
        when (terminatedOperation) {
            is PingGenerationStatus.SuccessPingGenerationStatus -> {
                // Add new image urls to current generation
                emit(
                    SKUGenerationStatus.SuccessGenerationStatus(
                        sourceImageUrl = sourceImageUrl,
                        imageUrls = terminatedOperation.imageUrls,
                    ),
                )
            }

            else -> {
                // Fallback with last success generation result
                emit(
                    SKUGenerationStatus.ErrorGenerationStatus(
                        errorMessage = (terminatedOperation as? PingGenerationStatus.ErrorPingGenerationStatus)?.errorMessage,
                        exception = (terminatedOperation as? PingGenerationStatus.ErrorPingGenerationStatus)?.exception,
                    ),
                )
            }
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
