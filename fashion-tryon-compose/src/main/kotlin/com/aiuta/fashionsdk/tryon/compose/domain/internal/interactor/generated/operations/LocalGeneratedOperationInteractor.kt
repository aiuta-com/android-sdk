package com.aiuta.fashionsdk.tryon.compose.domain.internal.interactor.generated.operations

import android.content.Context
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.filter
import androidx.paging.map
import com.aiuta.fashionsdk.tryon.compose.data.internal.datasource.generated.operations.GeneratedOperationDatasource
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.operations.GeneratedOperation
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.operations.toUiModel
import com.aiuta.fashionsdk.tryon.core.domain.models.SKUGenerationStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class LocalGeneratedOperationInteractor(
    private val generatedOperationDatasource: GeneratedOperationDatasource,
) : GeneratedOperationInteractor {
    override fun getGeneratedOperationFlow(): Flow<PagingData<GeneratedOperation>> {
        return Pager(
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
    }

    override suspend fun getFirstGeneratedOperation(): GeneratedOperation? {
        return generatedOperationDatasource.getFirstGeneratedOperationWithImages()?.toUiModel()
    }

    // Raw operation
    override suspend fun createOperation(imageId: String): Long {
        return generatedOperationDatasource.createOperation().id
    }

    override suspend fun deleteOperation(operation: GeneratedOperation) {
        generatedOperationDatasource.deleteOperation(operation.operationId)
    }

    override suspend fun deleteOperations(operations: List<GeneratedOperation>) {
        operations.forEach { operation -> deleteOperation(operation) }
    }

    override fun countGeneratedOperation(): Flow<Int> {
        return generatedOperationDatasource.countGeneratedOperation()
    }

    override suspend fun createImage(
        status: SKUGenerationStatus.LoadingGenerationStatus.UploadedSourceImage,
        operationId: Long,
    ) {
        generatedOperationDatasource.createImage(
            imageId = status.sourceImageId,
            imageUrl = status.sourceImageUrl,
            operationId = operationId,
        )
    }

    companion object {
        private const val DEFAULT_PAGE_SIZE = 10

        fun getInstance(context: Context): LocalGeneratedOperationInteractor {
            return LocalGeneratedOperationInteractor(
                generatedOperationDatasource =
                    GeneratedOperationDatasource.getInstance(
                        context = context,
                    ),
            )
        }
    }
}
