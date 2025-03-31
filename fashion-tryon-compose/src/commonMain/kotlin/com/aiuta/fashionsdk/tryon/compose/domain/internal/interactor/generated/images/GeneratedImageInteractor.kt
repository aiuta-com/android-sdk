package com.aiuta.fashionsdk.tryon.compose.domain.internal.interactor.generated.images

import androidx.paging.PagingData
import com.aiuta.fashionsdk.Aiuta
import com.aiuta.fashionsdk.tryon.compose.configuration.dataprovider.AiutaDataProvider
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.images.GeneratedImageUIModel
import kotlinx.coroutines.flow.Flow

internal interface GeneratedImageInteractor {
    suspend fun insertAll(
        generatedSkuId: String,
        images: List<GeneratedImageUIModel>,
    )

    fun generatedImagesFlow(): Flow<PagingData<GeneratedImageUIModel>>

    suspend fun remove(generatedImages: List<GeneratedImageUIModel>)

    suspend fun removeAll()

    fun countFlow(): Flow<Int>

    companion object {
        fun getInstance(aiuta: Aiuta): GeneratedImageInteractor {
            return LocalGeneratedImageInteractor.getInstance(aiuta.platformContext)
        }

        fun getInstance(dataProvider: AiutaDataProvider): GeneratedImageInteractor {
            return HostGeneratedImageInteractor.getInstance(dataProvider)
        }
    }
}
