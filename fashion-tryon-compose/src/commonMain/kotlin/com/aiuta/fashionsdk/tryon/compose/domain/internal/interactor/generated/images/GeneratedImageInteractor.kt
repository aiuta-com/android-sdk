package com.aiuta.fashionsdk.tryon.compose.domain.internal.interactor.generated.images

import androidx.paging.PagingData
import com.aiuta.fashionsdk.Aiuta
import com.aiuta.fashionsdk.tryon.compose.configuration.features.tryon.history.dataprovider.AiutaTryOnGenerationsHistoryFeatureDataProvider
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.images.GeneratedImageUIModel
import kotlinx.coroutines.flow.Flow

internal interface GeneratedImageInteractor {
    suspend fun insertAll(
        generatedProductId: String,
        images: List<GeneratedImageUIModel>,
    )

    fun generatedImagesFlow(): Flow<PagingData<GeneratedImageUIModel>>

    suspend fun remove(generatedImages: List<GeneratedImageUIModel>)

    suspend fun removeAll()

    fun countFlow(): Flow<Int>

    companion object {
        fun getInstance(aiuta: Aiuta): GeneratedImageInteractor = LocalGeneratedImageInteractor.getInstance(aiuta.platformContext)

        fun getInstance(dataProvider: AiutaTryOnGenerationsHistoryFeatureDataProvider): GeneratedImageInteractor = HostGeneratedImageInteractor.getInstance(dataProvider)
    }
}
