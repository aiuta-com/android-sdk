package com.aiuta.fashionsdk.tryon.compose.domain.internal.interactor.generated.images

import androidx.paging.PagingData
import com.aiuta.fashionsdk.context.AiutaPlatformContext
import com.aiuta.fashionsdk.tryon.compose.data.internal.datasource.generated.images.GeneratedImageDatasource
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.images.GeneratedImageUIModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

internal class EmptyGeneratedImageInteractor(
    private val generatedImageDatasource: GeneratedImageDatasource,
) : GeneratedImageInteractor {
    override suspend fun insertAll(
        generatedProductId: String,
        images: List<GeneratedImageUIModel>,
    ) {
        // Do nothing
    }

    override fun generatedImagesFlow(): Flow<PagingData<GeneratedImageUIModel>> = flowOf(PagingData.empty())

    override suspend fun remove(generatedImages: List<GeneratedImageUIModel>) {
        // Do nothing
    }

    override suspend fun removeAll() {
        // Only support remove local, because if it's empty - we should remove all local images
        generatedImageDatasource.removeAll()
    }

    override fun countFlow(): Flow<Int> = flowOf(0)

    companion object {
        fun getInstance(platformContext: AiutaPlatformContext): EmptyGeneratedImageInteractor = EmptyGeneratedImageInteractor(
            generatedImageDatasource = GeneratedImageDatasource.getInstance(
                platformContext = platformContext,
            ),
        )
    }
}
