package com.aiuta.fashionsdk.tryon.compose.domain.internal.interactor.generated.operations

import androidx.paging.PagingData
import com.aiuta.fashionsdk.tryon.compose.domain.models.dataprovider.AiutaDataProvider
import com.aiuta.fashionsdk.tryon.compose.domain.models.dataprovider.AiutaUploadedImage
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.images.SourceImage
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.operations.GeneratedOperation
import com.aiuta.fashionsdk.tryon.core.domain.models.SKUGenerationStatus
import kotlin.random.Random
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class HostGeneratedOperationInteractor(
    private val dataProvider: AiutaDataProvider,
) : GeneratedOperationInteractor {
    override fun getGeneratedOperationFlow(): Flow<PagingData<GeneratedOperation>> {
        return dataProvider.uploadedImagesFlow
            .map { images ->
                images.map { image ->
                    GeneratedOperation(
                        operationId = image.id.hashCode().toLong(),
                        sourceImages =
                            listOf(
                                SourceImage(
                                    imageId = image.id,
                                    imageUrl = image.url,
                                ),
                            ),
                    )
                }
            }
            .map { images -> PagingData.from(images) }
    }

    override suspend fun getFirstGeneratedOperation(): GeneratedOperation {
        val firstImage = dataProvider.uploadedImagesFlow.value.first()
        return GeneratedOperation(
            operationId = firstImage.id.hashCode().toLong(),
            sourceImages =
                listOf(
                    SourceImage(
                        imageId = firstImage.id,
                        imageUrl = firstImage.url,
                    ),
                ),
        )
    }

    override suspend fun createOperation(): Long {
        // Can use random, because operation is not used in host control mode
        return Random.nextLong()
    }

    override suspend fun deleteOperation(operation: GeneratedOperation) {
        dataProvider.deleteUploadedImagesAction(
            operation.sourceImages.map { image ->
                AiutaUploadedImage(
                    id = image.imageId,
                    url = image.imageUrl,
                )
            },
        )
    }

    override fun countGeneratedOperation(): Flow<Int> {
        return dataProvider.uploadedImagesFlow.map { images -> images.size }
    }

    override suspend fun createImage(
        status: SKUGenerationStatus.LoadingGenerationStatus.UploadedSourceImage,
        operationId: Long,
    ) {
        dataProvider.addUploadedImagesAction(
            listOf(
                AiutaUploadedImage(
                    id = status.sourceImageId,
                    url = status.sourceImageUrl,
                ),
            ),
        )
    }

    companion object {
        fun getInstance(dataProvider: AiutaDataProvider): HostGeneratedOperationInteractor {
            return HostGeneratedOperationInteractor(dataProvider = dataProvider)
        }
    }
}
