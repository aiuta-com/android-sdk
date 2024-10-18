package com.aiuta.fashionsdk.tryon.compose.domain.internal.interactor.generated.images

import androidx.paging.PagingData
import com.aiuta.fashionsdk.tryon.compose.domain.models.dataprovider.AiutaDataProvider
import com.aiuta.fashionsdk.tryon.compose.domain.models.dataprovider.AiutaGeneratedImage
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.images.GeneratedImage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class HostGeneratedImageInteractor(
    private val dataProvider: AiutaDataProvider,
) : GeneratedImageInteractor {
    override suspend fun insertAll(imageUrls: List<String>) {
        dataProvider.addGeneratedImagesAction(
            imageUrls.map { url -> AiutaGeneratedImage(url) },
        )
    }

    override fun generatedImagesFlow(): Flow<PagingData<GeneratedImage>> {
        return dataProvider.generatedImagesFlow
            .map { images ->
                images.map { image ->
                    GeneratedImage(id = image.url.hashCode().toLong(), imageUrl = image.url)
                }
            }
            .map { images -> PagingData.from(images) }
    }

    override suspend fun remove(generatedImages: List<GeneratedImage>) {
        dataProvider.deleteGeneratedImagesAction(
            generatedImages.map { image -> AiutaGeneratedImage(image.imageUrl) },
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
