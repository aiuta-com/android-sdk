package com.aiuta.fashionsdk.tryon.compose.domain.internal.interactor.generated.images

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.aiuta.fashionsdk.context.AiutaPlatformContext
import com.aiuta.fashionsdk.tryon.compose.data.internal.datasource.generated.images.GeneratedImageDatasource
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.images.GeneratedImageUIModel
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.images.toEntity
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.images.toUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class LocalGeneratedImageInteractor(
    private val generatedImageDatasource: GeneratedImageDatasource,
) : GeneratedImageInteractor {
    override suspend fun insertAll(
        generatedProductId: String,
        images: List<GeneratedImageUIModel>,
    ) = generatedImageDatasource.insertAll(
        generatedImages = images.map { it.toEntity() },
    )

    override fun generatedImagesFlow(): Flow<PagingData<GeneratedImageUIModel>> = Pager(
        config =
        PagingConfig(
            pageSize = DEFAULT_PAGE_SIZE,
        ),
        pagingSourceFactory = {
            generatedImageDatasource.pagingSource()
        },
    )
        .flow
        .map { pagingData ->
            pagingData.map { it.toUiModel() }
        }

    override suspend fun remove(generatedImages: List<GeneratedImageUIModel>) {
        generatedImageDatasource.remove(
            generatedImageIds = generatedImages.map { it.id },
        )
    }

    override suspend fun removeAll() = generatedImageDatasource.removeAll()

    override fun countFlow(): Flow<Int> = generatedImageDatasource.countFlow()

    companion object {
        private const val DEFAULT_PAGE_SIZE = 10

        fun getInstance(platformContext: AiutaPlatformContext): LocalGeneratedImageInteractor = LocalGeneratedImageInteractor(
            generatedImageDatasource =
            GeneratedImageDatasource.getInstance(
                platformContext = platformContext,
            ),
        )
    }
}
