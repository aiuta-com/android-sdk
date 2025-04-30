package com.aiuta.fashionsdk.tryon.compose.domain.internal.interactor.generated.operations

import androidx.paging.PagingData
import com.aiuta.fashionsdk.Aiuta
import com.aiuta.fashionsdk.configuration.features.picker.history.AiutaImagePickerUploadsHistoryFeature
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.operations.GeneratedOperationUIModel
import com.aiuta.fashionsdk.tryon.core.data.datasource.image.models.AiutaFileType
import kotlinx.coroutines.flow.Flow

internal interface GeneratedOperationInteractor {
    fun getGeneratedOperationFlow(): Flow<PagingData<GeneratedOperationUIModel>>

    suspend fun getFirstGeneratedOperation(): GeneratedOperationUIModel?

    // Raw operation
    suspend fun createOperation(imageId: String): String

    suspend fun deleteOperation(operation: GeneratedOperationUIModel): Result<Unit>

    suspend fun deleteOperations(operations: List<GeneratedOperationUIModel>): Result<Unit>

    fun countGeneratedOperation(): Flow<Int>

    suspend fun createImage(
        sourceImageId: String,
        sourceImageUrl: String,
        sourceImageType: AiutaFileType,
        operationId: String,
    ): Result<Unit>

    companion object {
        fun getInstance(
            aiuta: Aiuta,
            uploadsHistoryFeature: AiutaImagePickerUploadsHistoryFeature?,
        ): GeneratedOperationInteractor = when {
            // Feature not initialized
            uploadsHistoryFeature == null -> EmptyGeneratedOperationInteractor()
            // Data provider not initialized -> let's use local
            uploadsHistoryFeature.dataProvider == null -> DatabaseGeneratedOperationInteractor.getInstance(
                platformContext = aiuta.platformContext,
            )
            // Data provider initialized -> let's use host
            else -> HostGeneratedOperationInteractor.getInstance(
                dataProvider = uploadsHistoryFeature.dataProvider!!,
            )
        }
    }
}

// For all implementations, which not expose result to host and save data locally
internal interface LocalGeneratedOperationInteractor : GeneratedOperationInteractor

internal fun GeneratedOperationInteractor.isEmptyInteractor(): Boolean = this is EmptyGeneratedOperationInteractor
