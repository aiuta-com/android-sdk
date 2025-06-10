package com.aiuta.fashionsdk.tryon.compose.data.internal.datasource.generated.operations

import androidx.paging.PagingSource
import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToOne
import com.aiuta.fashionsdk.tryon.compose.data.internal.database.AiutaTryOnDatabase
import com.aiuta.fashionsdk.tryon.compose.data.internal.database.AiutaTryOnDatabaseFactory
import com.aiuta.fashionsdk.tryon.compose.data.internal.entity.local.generated.images.SourceImageEntity
import com.aiuta.fashionsdk.tryon.compose.data.internal.entity.local.generated.images.toEntity
import com.aiuta.fashionsdk.tryon.compose.data.internal.entity.local.generated.operations.GeneratedOperationWithImagesEntity
import com.aiuta.fashionsdk.tryon.compose.data.internal.utils.QueryPagingSource
import com.aiuta.fashionsdk.tryon.core.data.datasource.image.models.AiutaFileType
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

internal class GeneratedOperationDatasource(
    private val database: AiutaTryOnDatabase,
) {
    private val generatedOperationQueries by lazy { database.generatedOperationQueries }
    private val sourceImageQueries by lazy { database.sourceImageQueries }

    // Combined operation
    fun pagingGeneratedOperations(): PagingSource<Int, String> = QueryPagingSource(
        countQuery = generatedOperationQueries.count(),
        transacter = generatedOperationQueries,
        queryProvider = generatedOperationQueries::selectAllWithPaging,
    )

    suspend fun getGeneratedOperationWithImages(): List<GeneratedOperationWithImagesEntity> = withContext(Dispatchers.IO) {
        val operationIds = generatedOperationQueries.selectAll().executeAsList()

        return@withContext operationIds.map { operationId ->
            GeneratedOperationWithImagesEntity(
                operationId = operationId,
                sourceImages = sourceImageQueries
                    .selectByOperationId(operation_id = operationId)
                    .executeAsList()
                    .map { it.toEntity() },
            )
        }
    }

    suspend fun getSourceImagesByOperationId(operationId: String): List<SourceImageEntity> = withContext(Dispatchers.IO) {
        sourceImageQueries
            .selectByOperationId(operation_id = operationId)
            .executeAsList()
            .map { it.toEntity() }
    }

    // Raw operation
    @OptIn(ExperimentalUuidApi::class)
    suspend fun createOperation(): String = withContext(Dispatchers.IO) {
        val operationId = Uuid.random().toString()
        generatedOperationQueries.insert(operationId)

        operationId
    }

    suspend fun deleteOperation(operationId: String) = withContext(Dispatchers.IO) {
        generatedOperationQueries.removeById(operationId)
        sourceImageQueries.removeByOperationId(operationId)
    }

    fun countGeneratedOperation(): Flow<Long> = generatedOperationQueries
        .count()
        .asFlow()
        .mapToOne(Dispatchers.IO)

    // Source images
    suspend fun createImage(
        imageId: String,
        imageUrl: String,
        imageType: AiutaFileType,
        operationId: String,
    ) = withContext(Dispatchers.IO) {
        sourceImageQueries.insert(
            id = imageId,
            operation_id = operationId,
            imageUrl = imageUrl,
            imageType = imageType,
        )
    }

    companion object {
        fun getInstance(): GeneratedOperationDatasource = GeneratedOperationDatasource(
            database = AiutaTryOnDatabaseFactory.getInstance(),
        )
    }
}
