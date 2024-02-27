package com.aiuta.fashionsdk.tryon.core.data.datasource.operation

import com.aiuta.fashionsdk.network.paging.models.PageContainer
import com.aiuta.fashionsdk.network.paging.models.PaginationOffset
import com.aiuta.fashionsdk.tryon.core.data.datasource.operation.models.CreateSKUOperationRequest
import com.aiuta.fashionsdk.tryon.core.data.datasource.operation.models.CreateSKUOperationResponse
import com.aiuta.fashionsdk.tryon.core.data.datasource.operation.models.SKUOperation

internal interface FashionSKUOperationsDataSource {
    suspend fun createSKUOperation(request: CreateSKUOperationRequest): CreateSKUOperationResponse

    suspend fun getSKUOperations(
        paginationOffset: PaginationOffset? = null,
        paginationLimit: Int? = null,
    ): PageContainer<SKUOperation>

    suspend fun getSKUOperation(operationId: String): SKUOperation
}
