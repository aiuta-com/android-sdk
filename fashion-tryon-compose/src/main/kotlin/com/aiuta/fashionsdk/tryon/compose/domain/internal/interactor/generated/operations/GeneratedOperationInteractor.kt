package com.aiuta.fashionsdk.tryon.compose.domain.internal.interactor.generated.operations

import android.content.Context
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.filter
import androidx.paging.map
import com.aiuta.fashionsdk.tryon.compose.data.internal.datasource.generated.operations.GeneratedOperationDatasource
import com.aiuta.fashionsdk.tryon.compose.domain.models.GeneratedOperation
import com.aiuta.fashionsdk.tryon.compose.domain.models.toUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map

internal class GeneratedOperationInteractor(
    private val generatedOperationDatasource: GeneratedOperationDatasource,
) {
    fun getGeneratedOperationFlow(): Flow<PagingData<GeneratedOperation>> {
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

    suspend fun getLastGeneratedOperation(): GeneratedOperation {
        return generatedOperationDatasource.getLastGeneratedOperationWithImages().toUiModel()
    }

    // Raw operation
    suspend fun createOperation(): Long {
        return generatedOperationDatasource.createOperation().id
    }

    suspend fun deleteOperation(operation: GeneratedOperation) {
        generatedOperationDatasource.deleteOperation(operation.operationId)
    }

    fun countGeneratedOperation(): Flow<Int> {
        return generatedOperationDatasource.countGeneratedOperation()
    }

    suspend fun createImage(
        imageUrl: String,
        operationId: Long,
    ) {
        generatedOperationDatasource.createImage(
            imageUrl = imageUrl,
            operationId = operationId,
        )
    }

    companion object {
        private const val DEFAULT_PAGE_SIZE = 10

        fun getInstance(context: Context): GeneratedOperationInteractor {
            return GeneratedOperationInteractor(
                generatedOperationDatasource =
                    GeneratedOperationDatasource.getInstance(
                        context = context,
                    ),
            )
        }
    }
}
