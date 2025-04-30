package com.aiuta.fashionsdk.tryon.compose.domain.internal.interactor.generated.operations

import androidx.paging.PagingData
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.operations.GeneratedOperationUIModel
import com.aiuta.fashionsdk.tryon.core.data.datasource.image.models.AiutaFileType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

internal class EmptyGeneratedOperationInteractor : LocalGeneratedOperationInteractor {

    override fun getGeneratedOperationFlow(): Flow<PagingData<GeneratedOperationUIModel>> = flowOf(PagingData.empty())

    override suspend fun getFirstGeneratedOperation(): GeneratedOperationUIModel? = null

    override suspend fun createOperation(imageId: String): String = imageId

    override suspend fun deleteOperation(operation: GeneratedOperationUIModel): Result<Unit> = Result.success(Unit)

    override suspend fun deleteOperations(operations: List<GeneratedOperationUIModel>): Result<Unit> = Result.success(Unit)

    override fun countGeneratedOperation(): Flow<Int> = flowOf(0)

    override suspend fun createImage(
        sourceImageId: String,
        sourceImageUrl: String,
        sourceImageType: AiutaFileType,
        operationId: String,
    ): Result<Unit> = Result.success(Unit)
}
