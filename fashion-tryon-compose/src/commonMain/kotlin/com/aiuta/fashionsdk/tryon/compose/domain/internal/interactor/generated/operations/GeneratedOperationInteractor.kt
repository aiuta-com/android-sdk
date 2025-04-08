package com.aiuta.fashionsdk.tryon.compose.domain.internal.interactor.generated.operations

import androidx.paging.PagingData
import com.aiuta.fashionsdk.Aiuta
import com.aiuta.fashionsdk.tryon.compose.configuration.features.selector.history.AiutaImageSelectorUploadsHistoryFeature
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.operations.GeneratedOperationUIModel
import kotlinx.coroutines.flow.Flow

internal interface GeneratedOperationInteractor {
    fun getGeneratedOperationFlow(): Flow<PagingData<GeneratedOperationUIModel>>

    suspend fun getFirstGeneratedOperation(): GeneratedOperationUIModel?

    // Raw operation
    suspend fun createOperation(imageId: String): String

    suspend fun deleteOperation(operation: GeneratedOperationUIModel)

    suspend fun deleteOperations(operations: List<GeneratedOperationUIModel>)

    fun countGeneratedOperation(): Flow<Int>

    suspend fun createImage(
        sourceImageId: String,
        sourceImageUrl: String,
        operationId: String,
    )

    companion object {
        fun getInstance(
            aiuta: Aiuta,
            uploadsHistoryFeature: AiutaImageSelectorUploadsHistoryFeature?,
        ): GeneratedOperationInteractor = when {
            // Feature not initialized
            uploadsHistoryFeature == null -> EmptyGeneratedOperationInteractor()
            // Data provider not initialized -> let's use local
            uploadsHistoryFeature.dataProvider == null -> LocalGeneratedOperationInteractor.getInstance(
                platformContext = aiuta.platformContext,
            )
            // Data provider initialized -> let's use host
            else -> HostGeneratedOperationInteractor.getInstance(
                dataProvider = uploadsHistoryFeature.dataProvider!!,
            )
        }
    }
}

internal fun GeneratedOperationInteractor.isEmptyInteractor(): Boolean = this is EmptyGeneratedOperationInteractor
