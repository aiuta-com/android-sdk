package com.aiuta.fashionsdk.tryon.compose.domain.internal.interactor.generated.operations

import androidx.paging.PagingData
import com.aiuta.fashionsdk.Aiuta
import com.aiuta.fashionsdk.tryon.compose.domain.models.configuration.dataprovider.AiutaDataProvider
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
        fun getInstance(aiuta: Aiuta): GeneratedOperationInteractor {
            return LocalGeneratedOperationInteractor.getInstance(aiuta.platformContext)
        }

        fun getInstance(dataProvider: AiutaDataProvider): GeneratedOperationInteractor {
            return HostGeneratedOperationInteractor.getInstance(dataProvider)
        }
    }
}
