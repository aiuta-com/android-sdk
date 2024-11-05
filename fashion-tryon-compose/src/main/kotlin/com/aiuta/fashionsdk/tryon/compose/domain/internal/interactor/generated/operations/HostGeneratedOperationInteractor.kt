package com.aiuta.fashionsdk.tryon.compose.domain.internal.interactor.generated.operations

import androidx.paging.PagingData
import com.aiuta.fashionsdk.tryon.compose.domain.models.dataprovider.AiutaDataProvider
import com.aiuta.fashionsdk.tryon.compose.domain.models.dataprovider.AiutaUploadedImage
import com.aiuta.fashionsdk.tryon.compose.domain.models.dataprovider.toAiutaUploadedImage
import com.aiuta.fashionsdk.tryon.compose.domain.models.dataprovider.toGeneratedOperation
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.operations.GeneratedOperation
import com.aiuta.fashionsdk.tryon.core.domain.models.SKUGenerationStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class HostGeneratedOperationInteractor(
    private val dataProvider: AiutaDataProvider,
) : GeneratedOperationInteractor {
    override fun getGeneratedOperationFlow(): Flow<PagingData<GeneratedOperation>> {
        return dataProvider.uploadedImagesFlow
            .map { images ->
                images.map { image -> image.toGeneratedOperation() }
            }
            .map { images -> PagingData.from(images) }
    }

    override suspend fun getFirstGeneratedOperation(): GeneratedOperation? {
        val firstImage = dataProvider.uploadedImagesFlow.value.firstOrNull()
        return firstImage?.let { firstImage.toGeneratedOperation() }
    }

    override suspend fun createOperation(imageId: String): Long {
        // Can use hashcode, because operation is not used in host control mode
        return imageId.hashCode().toLong()
    }

    override suspend fun deleteOperation(operation: GeneratedOperation) {
        dataProvider.deleteUploadedImagesAction(operation.toAiutaUploadedImage())
    }

    override suspend fun deleteOperations(operations: List<GeneratedOperation>) {
        dataProvider.deleteUploadedImagesAction(
            operations
                .map { it.toAiutaUploadedImage() }
                .flatten(),
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
