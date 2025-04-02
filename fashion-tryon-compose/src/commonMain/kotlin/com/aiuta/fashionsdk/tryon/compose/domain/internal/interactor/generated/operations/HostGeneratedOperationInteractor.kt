package com.aiuta.fashionsdk.tryon.compose.domain.internal.interactor.generated.operations

import androidx.paging.PagingData
import com.aiuta.fashionsdk.tryon.compose.configuration.dataprovider.AiutaHistoryImage
import com.aiuta.fashionsdk.tryon.compose.configuration.features.selector.history.dataprovider.AiutaImageSelectorUploadsHistoryFeatureDataProvider
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.operations.GeneratedOperationUIModel
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.operations.toOperationUiModel
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.operations.toPublic
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class HostGeneratedOperationInteractor(
    private val dataProvider: AiutaImageSelectorUploadsHistoryFeatureDataProvider,
) : GeneratedOperationInteractor {
    override fun getGeneratedOperationFlow(): Flow<PagingData<GeneratedOperationUIModel>> = dataProvider.uploadedImages
        .map { images ->
            images.map { image -> image.toOperationUiModel() }
        }
        .map { images -> PagingData.from(images) }

    override suspend fun getFirstGeneratedOperation(): GeneratedOperationUIModel? {
        val firstImage = dataProvider.uploadedImages.value.firstOrNull()
        return firstImage?.let { firstImage.toOperationUiModel() }
    }

    override suspend fun createOperation(imageId: String): String = imageId

    override suspend fun deleteOperation(operation: GeneratedOperationUIModel) {
        dataProvider.deleteUploadedImagesAction(operation.toPublic())
    }

    override suspend fun deleteOperations(operations: List<GeneratedOperationUIModel>) {
        dataProvider.deleteUploadedImagesAction(
            operations
                .map { it.toPublic() }
                .flatten(),
        )
    }

    override fun countGeneratedOperation(): Flow<Int> = dataProvider.uploadedImages.map { images -> images.size }

    override suspend fun createImage(
        sourceImageId: String,
        sourceImageUrl: String,
        operationId: String,
    ) {
        dataProvider.addUploadedImagesAction(
            listOf(
                AiutaHistoryImage(
                    id = sourceImageId,
                    url = sourceImageUrl,
                ),
            ),
        )
    }

    companion object {
        fun getInstance(dataProvider: AiutaImageSelectorUploadsHistoryFeatureDataProvider): HostGeneratedOperationInteractor = HostGeneratedOperationInteractor(dataProvider = dataProvider)
    }
}
