package com.aiuta.fashionsdk.tryon.compose.domain.internal.interactor.generated.images

import android.content.Context
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
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
        generatedSkuId: String,
        images: List<GeneratedImageUIModel>,
    ) {
        return generatedImageDatasource.insertAll(
            generatedImages = images.map { it.toEntity() },
        )
    }

    override fun generatedImagesFlow(): Flow<PagingData<GeneratedImageUIModel>> {
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

    override suspend fun remove(generatedImages: List<GeneratedImageUIModel>) {
        generatedImageDatasource.remove(
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
