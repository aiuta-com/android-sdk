package com.aiuta.fashionsdk.tryon.compose.domain.internal.interactor

import android.content.Context
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.aiuta.fashionsdk.tryon.compose.data.internal.datasource.GeneratedImageDatasource
import com.aiuta.fashionsdk.tryon.compose.data.internal.entity.GeneratedImageEntity
import com.aiuta.fashionsdk.tryon.compose.domain.models.GeneratedImage
import com.aiuta.fashionsdk.tryon.compose.domain.models.toUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class GeneratedImageInteractor(
    private val generatedImageDatasource: GeneratedImageDatasource,
) {
    suspend fun insertAll(imageUrls: List<String>) {
        return generatedImageDatasource.insertAll(
            generatedImages =
                imageUrls.map { imageUrl ->
                    GeneratedImageEntity(
                        imageUrl = imageUrl,
                    )
                },
        )
    }

    fun generatedImagesFlow(): Flow<PagingData<GeneratedImage>> {
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

    suspend fun remove(generatedImages: List<GeneratedImage>) {
        return generatedImageDatasource.remove(
            generatedImageIds = generatedImages.map { it.id },
        )
    }

    fun count(): Flow<Int> {
        return generatedImageDatasource.count()
    }

    companion object {
        private const val DEFAULT_PAGE_SIZE = 10

        fun getInstance(context: Context): GeneratedImageInteractor {
            return GeneratedImageInteractor(
                generatedImageDatasource =
                    GeneratedImageDatasource.getInstance(
                        context = context,
                    ),
            )
        }
    }
}
