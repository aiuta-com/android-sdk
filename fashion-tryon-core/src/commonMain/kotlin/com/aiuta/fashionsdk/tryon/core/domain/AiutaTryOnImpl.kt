package com.aiuta.fashionsdk.tryon.core.domain

import com.aiuta.fashionsdk.Aiuta
import com.aiuta.fashionsdk.internal.analytic.InternalAiutaAnalytic
import com.aiuta.fashionsdk.internal.analytic.internalAiutaAnalytic
import com.aiuta.fashionsdk.network.paging.models.PageContainer
import com.aiuta.fashionsdk.network.paging.models.PaginationOffset
import com.aiuta.fashionsdk.tryon.core.AiutaTryOn
import com.aiuta.fashionsdk.tryon.core.data.datasource.image.models.UploadedImage
import com.aiuta.fashionsdk.tryon.core.data.datasource.operation.FashionSKUOperationsDataSource
import com.aiuta.fashionsdk.tryon.core.data.datasource.operation.models.CreateSKUOperationRequest
import com.aiuta.fashionsdk.tryon.core.data.datasource.operation.skuOperationsDataSourceFactory
import com.aiuta.fashionsdk.tryon.core.data.datasource.sku.FashionSKUDataSource
import com.aiuta.fashionsdk.tryon.core.data.datasource.sku.skuDataSourceFactory
import com.aiuta.fashionsdk.tryon.core.domain.analytic.sendStartTryOnEvent
import com.aiuta.fashionsdk.tryon.core.domain.analytic.sendTryOnPhotoUploadedEvent
import com.aiuta.fashionsdk.tryon.core.domain.models.SKUCatalog
import com.aiuta.fashionsdk.tryon.core.domain.models.SKUGenerationContainer
import com.aiuta.fashionsdk.tryon.core.domain.models.SKUGenerationItem
import com.aiuta.fashionsdk.tryon.core.domain.models.SKUGenerationStatus
import com.aiuta.fashionsdk.tryon.core.domain.models.SKUGenerationPlatformImageContainer
import com.aiuta.fashionsdk.tryon.core.domain.models.SKUGenerationUrlContainer
import com.aiuta.fashionsdk.tryon.core.domain.models.meta.AiutaTryOnMetadata
import com.aiuta.fashionsdk.tryon.core.domain.models.policies.AiutaTryOnRetryPolicies
import com.aiuta.fashionsdk.tryon.core.domain.models.policies.DefaultAiutaTryOnRetryPolicies
import com.aiuta.fashionsdk.tryon.core.domain.models.toPublic
import com.aiuta.fashionsdk.tryon.core.domain.slice.ping.PingOperationSlice
import com.aiuta.fashionsdk.tryon.core.domain.slice.ping.exception.AiutaTryOnExceptionType
import com.aiuta.fashionsdk.tryon.core.domain.slice.ping.pingOperationSliceFactory
import com.aiuta.fashionsdk.tryon.core.domain.slice.upload.UploadImageSlice
import com.aiuta.fashionsdk.tryon.core.domain.slice.upload.uploadImageSliceFactory
import com.aiuta.fashionsdk.tryon.core.domain.utils.errorListener
import com.aiuta.fashionsdk.tryon.core.domain.utils.trackSpecificTryOnArea
import com.aiuta.fashionsdk.tryon.core.domain.utils.trackTryOnArea
import com.aiuta.fashionsdk.tryon.core.utils.generateFileName
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class AiutaTryOnImpl(
    internal val analytic: InternalAiutaAnalytic,
    internal val retryPolicies: AiutaTryOnRetryPolicies,
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
            analytic.sendStartTryOnEvent(container)
            errorListener {
                val metadataBuilder = AiutaTryOnMetadata.Builder()

                // Set loading state with previous image urls
                emit(SKUGenerationStatus.LoadingGenerationStatus.StartGeneration)

                // Firstly, upload image on backend
                val uploadedImage =
                    trackTryOnArea(
                        typeArea = AiutaTryOnExceptionType.UPLOAD_PHOTO_FAILED,
                        container = container,
                    ) {
                        solveUploadingImage(container)
                    }
                // Update time for meta info of generation
                metadataBuilder.setUploadDuration()
                emit(
                    SKUGenerationStatus.LoadingGenerationStatus.UploadedSourceImage(
                        sourceImageId = uploadedImage.id,
                        sourceImageUrl = uploadedImage.url,
                    ),
                )

                // Secondly, create sku generation operation
                val newOperation =
                    trackTryOnArea(
                        typeArea = AiutaTryOnExceptionType.START_OPERATION_FAILED,
                        container = container,
                    ) {
                        skuOperationsDataSource.createSKUOperation(
                            request =
                                CreateSKUOperationRequest(
                                    skuCatalogName = container.skuCatalogName,
                                    skuId = container.skuId,
                                    uploadedImageId = uploadedImage.id,
                                ),
                        )
                    }

                // Wait for the operation, until it is completed
                emit(
                    SKUGenerationStatus.LoadingGenerationStatus.GenerationProcessing(
                        sourceImageId = uploadedImage.id,
                        sourceImageUrl = uploadedImage.url,
                    ),
                )
                val generations =
                    trackSpecificTryOnArea(
                        typeArea = AiutaTryOnExceptionType.OPERATION_FAILED,
                        failingTypes =
                            setOf(
                                AiutaTryOnExceptionType.OPERATION_ABORTED_FAILED,
                                AiutaTryOnExceptionType.OPERATION_TIMEOUT_FAILED,
                            ),
                        container = container,
                    ) {
                        pingOperationSlice.operationPing(newOperation.operationId)
                    }
                // Update time for meta info of generation
                metadataBuilder.setTryOnDuration()

                // Finally, emit result
                emit(
                    SKUGenerationStatus.SuccessGenerationStatus(
                        sourceImageId = uploadedImage.id,
                        sourceImageUrl = uploadedImage.url,
                        images = generations.map { it.toPublic() },
                        metadata = metadataBuilder.build(),
                    ),
                )
            }
        }
    }

    private suspend fun solveUploadingImage(container: SKUGenerationContainer): UploadedImage {
        return when (container) {
            is SKUGenerationPlatformImageContainer -> {
                uploadImageSlice.uploadImage(
                    container = container,
                    fileName = generateFileName(),
                ).also {
                    analytic.sendTryOnPhotoUploadedEvent(container)
                }
            }

            is SKUGenerationUrlContainer -> {
                UploadedImage(
                    id = container.fileId,
                    url = container.fileUrl,
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
                retryPolicies = DefaultAiutaTryOnRetryPolicies,
            )
        }
    }
}
