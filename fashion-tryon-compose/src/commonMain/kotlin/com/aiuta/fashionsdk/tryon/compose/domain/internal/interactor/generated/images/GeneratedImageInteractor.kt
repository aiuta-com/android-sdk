package com.aiuta.fashionsdk.tryon.compose.domain.internal.interactor.generated.images

import androidx.paging.PagingData
import com.aiuta.fashionsdk.Aiuta
import com.aiuta.fashionsdk.configuration.features.features.tryon.history.AiutaTryOnGenerationsHistoryFeature
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.images.GeneratedImageUIModel
import kotlinx.coroutines.flow.Flow

internal interface GeneratedImageInteractor {
    suspend fun insertAll(
        generatedProductId: String,
        images: List<GeneratedImageUIModel>,
    ): Result<Unit>

    fun generatedImagesFlow(): Flow<PagingData<GeneratedImageUIModel>>

    suspend fun remove(generatedImages: List<GeneratedImageUIModel>): Result<Unit>

    suspend fun removeAll(): Result<Unit>

    fun countFlow(): Flow<Int>

    companion object {
        fun getInstance(
            aiuta: Aiuta,
            generationsHistoryFeature: AiutaTryOnGenerationsHistoryFeature?,
        ): GeneratedImageInteractor = when {
            // Feature not initialized
            generationsHistoryFeature == null -> EmptyGeneratedImageInteractor.getInstance(
                platformContext = aiuta.platformContext,
            )
            // Data provider not initialized -> let's use local
            generationsHistoryFeature.dataProvider == null -> DatabaseGeneratedImageInteractor.getInstance(
                platformContext = aiuta.platformContext,
            )
            // Data provider initialized -> let's use host
            else -> HostGeneratedImageInteractor.getInstance(
                dataProvider = generationsHistoryFeature.dataProvider!!,
            )
        }
    }
}

// For all implementations, which not expose result to host and save data locally
internal interface LocalGeneratedImageInteractor : GeneratedImageInteractor
