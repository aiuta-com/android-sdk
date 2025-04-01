package com.aiuta.fashionsdk.tryon.compose.domain.internal.interactor.generated.operations

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.filter
import androidx.paging.map
import com.aiuta.fashionsdk.context.AiutaPlatformContext
import com.aiuta.fashionsdk.tryon.compose.data.internal.datasource.generated.operations.GeneratedOperationDatasource
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.operations.GeneratedOperationUIModel
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.operations.toUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class LocalGeneratedOperationInteractor(
    private val generatedOperationDatasource: GeneratedOperationDatasource,
) : GeneratedOperationInteractor {
    override fun getGeneratedOperationFlow(): Flow<PagingData<GeneratedOperationUIModel>> = Pager(
        config =
        PagingConfig(
            pageSize = DEFAULT_PAGE_SIZE,
        ),
        pagingSourceFactory = {
            generatedOperationDatasource.pagingGeneratedOperationWithImagesSource()
        },
    )
        .flow
        .map { pagingData ->
            pagingData.filter { it.sourceImages.isNotEmpty() }
        }
        .map { pagingData ->
            pagingData.map { it.toUiModel() }
        }

    override suspend fun getFirstGeneratedOperation(): GeneratedOperationUIModel? = generatedOperationDatasource.getFirstGeneratedOperationWithImages()?.toUiModel()

    // Raw operation
    override suspend fun createOperation(imageId: String): String = generatedOperationDatasource.createOperation().id

    override suspend fun deleteOperation(operation: GeneratedOperationUIModel) {
        generatedOperationDatasource.deleteOperation(operation.operationId)
    }

    override suspend fun deleteOperations(operations: List<GeneratedOperationUIModel>) {
        operations.forEach { operation -> deleteOperation(operation) }
    }

    override fun countGeneratedOperation(): Flow<Int> = generatedOperationDatasource.countGeneratedOperation()

    override suspend fun createImage(
        sourceImageId: String,
        sourceImageUrl: String,
        operationId: String,
    ) {
        generatedOperationDatasource.createImage(
            imageId = sourceImageId,
            imageUrl = sourceImageUrl,
            operationId = operationId,
        )
    }

    companion object {
        private const val DEFAULT_PAGE_SIZE = 10

        fun getInstance(platformContext: AiutaPlatformContext): LocalGeneratedOperationInteractor = LocalGeneratedOperationInteractor(
            generatedOperationDatasource =
            GeneratedOperationDatasource.getInstance(
                platformContext = platformContext,
            ),
        )
    }
}
