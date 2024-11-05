package com.aiuta.fashionsdk.tryon.compose.domain.internal.interactor.generated.operations

import android.content.Context
import androidx.paging.PagingData
import com.aiuta.fashionsdk.tryon.compose.domain.models.dataprovider.AiutaDataProvider
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.operations.GeneratedOperation
import com.aiuta.fashionsdk.tryon.core.domain.models.SKUGenerationStatus
import kotlinx.coroutines.flow.Flow

internal interface GeneratedOperationInteractor {
    fun getGeneratedOperationFlow(): Flow<PagingData<GeneratedOperation>>

    suspend fun getFirstGeneratedOperation(): GeneratedOperation?

    // Raw operation
    suspend fun createOperation(imageId: String): Long

    suspend fun deleteOperation(operation: GeneratedOperation)

    suspend fun deleteOperations(operations: List<GeneratedOperation>)

    fun countGeneratedOperation(): Flow<Int>

    suspend fun createImage(
        status: SKUGenerationStatus.LoadingGenerationStatus.UploadedSourceImage,
        operationId: Long,
    )

    companion object {
        fun getInstance(context: Context): GeneratedOperationInteractor {
            return LocalGeneratedOperationInteractor.getInstance(context)
        }

        fun getInstance(dataProvider: AiutaDataProvider): GeneratedOperationInteractor {
            return HostGeneratedOperationInteractor.getInstance(dataProvider)
        }
    }
}
