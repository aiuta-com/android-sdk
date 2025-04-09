package com.aiuta.fashionsdk.tryon.core.data.datasource.operation

import com.aiuta.fashionsdk.network.paging.models.PageContainer
import com.aiuta.fashionsdk.network.paging.models.PaginationOffset
import com.aiuta.fashionsdk.tryon.core.data.datasource.operation.models.CreateProductOperationRequest
import com.aiuta.fashionsdk.tryon.core.data.datasource.operation.models.CreateProductOperationResponse
import com.aiuta.fashionsdk.tryon.core.data.datasource.operation.models.ProductOperation

internal interface FashionProductOperationsDataSource {
    suspend fun createProductOperation(request: CreateProductOperationRequest): CreateProductOperationResponse

    suspend fun getProductOperations(
        paginationOffset: PaginationOffset? = null,
        paginationLimit: Int? = null,
    ): PageContainer<ProductOperation>

    suspend fun getProductOperation(operationId: String): ProductOperation
}
