package com.aiuta.fashionsdk.tryon.compose.domain.internal.interactor.generated.images

import android.content.Context
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.aiuta.fashionsdk.tryon.compose.data.internal.datasource.generated.images.GeneratedImageDatasource
import com.aiuta.fashionsdk.tryon.compose.data.internal.entity.local.generated.images.GeneratedImageEntity
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.images.GeneratedImage
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.images.toUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class LocalGeneratedImageInteractor(
    private val generatedImageDatasource: GeneratedImageDatasource,
) : GeneratedImageInteractor {
    override suspend fun insertAll(imageUrls: List<String>) {
        return generatedImageDatasource.insertAll(
            generatedImages =
                imageUrls.map { imageUrl ->
                    GeneratedImageEntity(
                        imageUrl = imageUrl,
                    )
                },
        )
    }

    override fun generatedImagesFlow(): Flow<PagingData<GeneratedImage>> {
        return Pager(
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
    }

    override suspend fun remove(generatedImages: List<GeneratedImage>) {
        return generatedImageDatasource.remove(
            generatedImageIds = generatedImages.map { it.id },
        )
    }

    override suspend fun removeAll() {
        return generatedImageDatasource.removeAll()
    }

    override fun countFlow(): Flow<Int> {
        return generatedImageDatasource.countFlow()
    }

    companion object {
        private const val DEFAULT_PAGE_SIZE = 10

        fun getInstance(context: Context): LocalGeneratedImageInteractor {
            return LocalGeneratedImageInteractor(
                generatedImageDatasource =
                    GeneratedImageDatasource.getInstance(
                        context = context,
                    ),
            )
        }
    }
}
