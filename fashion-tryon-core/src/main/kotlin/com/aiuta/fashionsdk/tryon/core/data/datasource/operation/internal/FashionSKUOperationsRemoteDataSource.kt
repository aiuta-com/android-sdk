package com.aiuta.fashionsdk.tryon.core.data.datasource.operation.internal

import com.aiuta.fashionsdk.network.NetworkClient
import com.aiuta.fashionsdk.network.paging.models.PageContainer
import com.aiuta.fashionsdk.network.paging.models.PaginationOffset
import com.aiuta.fashionsdk.network.paging.utils.saveAppend
import com.aiuta.fashionsdk.network.utils.saveAppendLimit
import com.aiuta.fashionsdk.tryon.core.data.datasource.operation.FashionSKUOperationsDataSource
import com.aiuta.fashionsdk.tryon.core.data.datasource.operation.models.CreateSKUOperationRequest
import com.aiuta.fashionsdk.tryon.core.data.datasource.operation.models.CreateSKUOperationResponse
import com.aiuta.fashionsdk.tryon.core.data.datasource.operation.models.SKUOperation
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class FashionSKUOperationsRemoteDataSource(
    private val networkClient: NetworkClient,
) : FashionSKUOperationsDataSource {
    override suspend fun createSKUOperation(
        request: CreateSKUOperationRequest,
    ): CreateSKUOperationResponse {
        return withContext(Dispatchers.IO) {
            networkClient.httpClient.value.post(
                urlString = PATH_SKU_OPERATIONS,
            ) {
                setBody(request)
            }.body()
        }
    }

    override suspend fun getSKUOperations(
        paginationOffset: PaginationOffset?,
        paginationLimit: Int?,
    ): PageContainer<SKUOperation> {
        return withContext(Dispatchers.IO) {
            networkClient.httpClient.value.get(
                urlString = PATH_SKU_OPERATIONS,
            ) {
                url {
                    saveAppend(paginationOffset)
                    saveAppendLimit(paginationLimit)
                }
            }.body()
        }
    }

    override suspend fun getSKUOperation(operationId: String): SKUOperation {
        return withContext(Dispatchers.IO) {
            networkClient.httpClient.value.get(
                urlString = "$PATH_SKU_OPERATIONS/$operationId",
            ).body()
        }
    }

    private companion object {
        const val PATH_SKU_OPERATIONS = "/sku_images_operations"
    }
}
