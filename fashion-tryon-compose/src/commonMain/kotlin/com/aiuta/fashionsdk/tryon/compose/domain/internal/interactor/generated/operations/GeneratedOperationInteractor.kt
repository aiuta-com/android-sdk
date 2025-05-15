package com.aiuta.fashionsdk.tryon.compose.domain.internal.interactor.generated.operations

import androidx.paging.PagingData
import com.aiuta.fashionsdk.Aiuta
import com.aiuta.fashionsdk.configuration.features.picker.history.AiutaImagePickerUploadsHistoryFeature
import com.aiuta.fashionsdk.configuration.features.picker.history.dataprovider.AiutaImagePickerUploadsHistoryFeatureDataProviderBuiltIn
import com.aiuta.fashionsdk.configuration.features.picker.history.dataprovider.AiutaImagePickerUploadsHistoryFeatureDataProviderCustom
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
        ): GeneratedOperationInteractor = when (val dataProvider = uploadsHistoryFeature?.dataProvider) {
            null -> EmptyGeneratedOperationInteractor()

            is AiutaImagePickerUploadsHistoryFeatureDataProviderBuiltIn -> DatabaseGeneratedOperationInteractor.getInstance(
                platformContext = aiuta.platformContext,
            )

            is AiutaImagePickerUploadsHistoryFeatureDataProviderCustom -> HostGeneratedOperationInteractor.getInstance(
                dataProvider = dataProvider,
            )
        }
    }
}

// For all implementations, which not expose result to host and save data locally
internal interface LocalGeneratedOperationInteractor : GeneratedOperationInteractor

internal fun GeneratedOperationInteractor.isEmptyInteractor(): Boolean = this is EmptyGeneratedOperationInteractor
