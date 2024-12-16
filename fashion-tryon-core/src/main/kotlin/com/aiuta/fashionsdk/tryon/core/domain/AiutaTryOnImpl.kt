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
import com.aiuta.fashionsdk.tryon.core.domain.analytic.sendTryOnPhotoUploadedEvent
import com.aiuta.fashionsdk.tryon.core.domain.models.SKUCatalog
import com.aiuta.fashionsdk.tryon.core.domain.models.SKUGenerationContainer
import com.aiuta.fashionsdk.tryon.core.domain.models.SKUGenerationItem
import com.aiuta.fashionsdk.tryon.core.domain.models.SKUGenerationStatus
import com.aiuta.fashionsdk.tryon.core.domain.models.SKUGenerationUriContainer
import com.aiuta.fashionsdk.tryon.core.domain.models.SKUGenerationUrlContainer
import com.aiuta.fashionsdk.tryon.core.domain.models.policies.DefaultTryOnRetryPolicies
import com.aiuta.fashionsdk.tryon.core.domain.models.policies.TryOnRetryPolicies
import com.aiuta.fashionsdk.tryon.core.domain.models.toPublic
import com.aiuta.fashionsdk.tryon.core.domain.slice.ping.PingOperationSlice
import com.aiuta.fashionsdk.tryon.core.domain.slice.ping.exception.TryOnExceptionType
import com.aiuta.fashionsdk.tryon.core.domain.slice.ping.pingOperationSliceFactory
import com.aiuta.fashionsdk.tryon.core.domain.slice.upload.UploadImageSlice
import com.aiuta.fashionsdk.tryon.core.domain.slice.upload.uploadImageSliceFactory
import com.aiuta.fashionsdk.tryon.core.domain.utils.errorListener
import com.aiuta.fashionsdk.tryon.core.domain.utils.measureTryOn
import com.aiuta.fashionsdk.tryon.core.domain.utils.trackSpecificTryOnArea
import com.aiuta.fashionsdk.tryon.core.domain.utils.trackTryOnArea
import com.aiuta.fashionsdk.tryon.core.utils.generateFileName
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class AiutaTryOnImpl(
    internal val analytic: InternalAiutaAnalytic,
    internal val retryPolicies: TryOnRetryPolicies,
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
                        trackTryOnArea(
                            typeArea = TryOnExceptionType.UPLOAD_PHOTO_FAILED,
                            container = container,
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
                        trackTryOnArea(
                            typeArea = TryOnExceptionType.START_OPERATION_FAILED,
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
                            typeArea = TryOnExceptionType.OPERATION_FAILED,
                            failingTypes =
                                setOf(
                                    TryOnExceptionType.OPERATION_ABORTED_FAILED,
                                    TryOnExceptionType.OPERATION_TIMEOUT_FAILED,
                                ),
                            container = container,
                        ) {
                            pingOperationSlice.operationPing(newOperation.operationId)
                        }

                    // Finally, emit result
                    emit(
                        SKUGenerationStatus.SuccessGenerationStatus(
                            sourceImageId = uploadedImage.id,
                            sourceImageUrl = uploadedImage.url,
                            images = generations.map { it.toPublic() },
                        ),
                    )
                }
            }
        }
    }

    private suspend fun solveUploadingImage(container: SKUGenerationContainer): UploadedImage {
        return when (container) {
            is SKUGenerationUriContainer -> {
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
                retryPolicies = DefaultTryOnRetryPolicies,
            )
        }
    }
}
