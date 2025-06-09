package com.aiuta.fashionsdk.tryon.compose.data.internal.datasource.generated.images

import androidx.paging.PagingSource
import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToOne
import com.aiuta.fashionsdk.tryon.compose.data.internal.database.AiutaTryOnDatabase
import com.aiuta.fashionsdk.tryon.compose.data.internal.database.AiutaTryOnDatabaseFactory
import com.aiuta.fashionsdk.tryon.compose.data.internal.database.Generated_image
import com.aiuta.fashionsdk.tryon.compose.data.internal.entity.local.generated.images.GeneratedImageEntity
import com.aiuta.fashionsdk.tryon.compose.data.internal.utils.QueryPagingSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

internal class GeneratedImageDatasource(
    private val database: AiutaTryOnDatabase,
) {
    private val generatedImageQueries by lazy { database.generatedImageQueries }

    suspend fun insertAll(generatedImages: List<GeneratedImageEntity>) {
        withContext(Dispatchers.IO) {
            generatedImageQueries.transaction {
                generatedImages.forEach { image ->
                    generatedImageQueries.insert(
                        id = image.id,
                        imageUrl = image.imageUrl,
                        type = image.type,
                        productIds = image.productIds,
                    )
                }
            }
        }
    }

    fun pagingSource(): PagingSource<Int, Generated_image> = QueryPagingSource(
        countQuery = generatedImageQueries.count(),
        transacter = generatedImageQueries,
        queryProvider = generatedImageQueries::selectAll,
    )

    suspend fun remove(generatedImageIds: List<String>) {
        withContext(Dispatchers.IO) {
            generatedImageQueries.removeByIds(generatedImageIds)
        }
    }

    suspend fun removeAll() {
        withContext(Dispatchers.IO) {
            generatedImageQueries.removeAll()
        }
    }

    fun countFlow(): Flow<Long> = generatedImageQueries
        .count()
        .asFlow()
        .mapToOne(Dispatchers.IO)

    companion object {
        fun getInstance(): GeneratedImageDatasource = GeneratedImageDatasource(
            database = AiutaTryOnDatabaseFactory.getInstance(),
        )
    }
}
