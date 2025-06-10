package com.aiuta.fashionsdk.tryon.compose.domain.internal.interactor.generated.operations

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.filter
import androidx.paging.map
import com.aiuta.fashionsdk.tryon.compose.data.internal.datasource.generated.operations.GeneratedOperationDatasource
import com.aiuta.fashionsdk.tryon.compose.data.internal.entity.local.generated.operations.GeneratedOperationWithImagesEntity
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.operations.GeneratedOperationUIModel
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.operations.toUiModel
import com.aiuta.fashionsdk.tryon.core.data.datasource.image.models.AiutaFileType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class DatabaseGeneratedOperationInteractor(
    private val generatedOperationDatasource: GeneratedOperationDatasource,
) : LocalGeneratedOperationInteractor {
    override fun getGeneratedOperationFlow(): Flow<PagingData<GeneratedOperationUIModel>> = Pager(
        config = PagingConfig(
            pageSize = DEFAULT_PAGE_SIZE,
        ),
        pagingSourceFactory = {
            generatedOperationDatasource.pagingGeneratedOperations()
        },
    )
        .flow
        .map { pagingData ->
            pagingData.map { operationId ->
                GeneratedOperationWithImagesEntity(
                    operationId = operationId,
                    sourceImages = generatedOperationDatasource.getSourceImagesByOperationId(operationId),
                )
            }
        }
        .map { pagingData ->
            pagingData.filter { it.sourceImages.isNotEmpty() }
        }
        .map { pagingData ->
            pagingData.map { it.toUiModel() }
        }

    override suspend fun getFirstGeneratedOperation(): GeneratedOperationUIModel? = generatedOperationDatasource
        .getGeneratedOperationWithImages()
        .firstOrNull()
        ?.toUiModel()

    // Raw operation
    override suspend fun createOperation(imageId: String): String = generatedOperationDatasource.createOperation()

    override suspend fun deleteOperation(operation: GeneratedOperationUIModel): Result<Unit> = runCatching {
        generatedOperationDatasource.deleteOperation(operation.operationId)
    }

    override suspend fun deleteOperations(operations: List<GeneratedOperationUIModel>): Result<Unit> = runCatching {
        operations.forEach { operation ->
            val result = deleteOperation(operation)
            check(result.isSuccess)
        }
    }

    override fun countGeneratedOperation(): Flow<Long> = generatedOperationDatasource.countGeneratedOperation()

    override suspend fun createImage(
        sourceImageId: String,
        sourceImageUrl: String,
        sourceImageType: AiutaFileType,
        operationId: String,
    ): Result<Unit> = runCatching {
        generatedOperationDatasource.createImage(
            imageId = sourceImageId,
            imageUrl = sourceImageUrl,
            imageType = sourceImageType,
            operationId = operationId,
        )
    }

    companion object {
        private const val DEFAULT_PAGE_SIZE = 10

        fun getInstance(): DatabaseGeneratedOperationInteractor = DatabaseGeneratedOperationInteractor(
            generatedOperationDatasource = GeneratedOperationDatasource.getInstance(),
        )
    }
}
