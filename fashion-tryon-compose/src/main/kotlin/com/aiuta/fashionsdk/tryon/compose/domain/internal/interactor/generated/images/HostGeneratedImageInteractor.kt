package com.aiuta.fashionsdk.tryon.compose.domain.internal.interactor.generated.images

import androidx.paging.PagingData
import com.aiuta.fashionsdk.tryon.compose.domain.models.configuration.dataprovider.AiutaDataProvider
import com.aiuta.fashionsdk.tryon.compose.domain.models.configuration.dataprovider.toImageUiModel
import com.aiuta.fashionsdk.tryon.compose.domain.models.configuration.dataprovider.toPublic
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.images.GeneratedImageUIModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class HostGeneratedImageInteractor(
    private val dataProvider: AiutaDataProvider,
) : GeneratedImageInteractor {
    override suspend fun insertAll(images: List<GeneratedImageUIModel>) {
        dataProvider.addGeneratedImagesAction(
            images.map { image -> image.toPublic() },
        )
    }

    override fun generatedImagesFlow(): Flow<PagingData<GeneratedImageUIModel>> {
        return dataProvider.generatedImagesFlow
            .map { images ->
                images.map { image -> image.toImageUiModel() }
            }
            .map { images -> PagingData.from(images) }
    }

    override suspend fun remove(generatedImages: List<GeneratedImageUIModel>) {
        dataProvider.deleteGeneratedImagesAction(
            generatedImages.map { image -> image.toPublic() },
        )
    }

    override suspend fun removeAll() {
        // Ignore this, because host should solve current list of generations
    }

    override fun countFlow(): Flow<Int> {
        return dataProvider.generatedImagesFlow.map { images -> images.size }
    }

    companion object {
        fun getInstance(dataProvider: AiutaDataProvider): HostGeneratedImageInteractor {
            return HostGeneratedImageInteractor(dataProvider = dataProvider)
        }
    }
}
