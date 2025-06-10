package com.aiuta.fashionsdk.tryon.compose.data.internal.datasource.time

import com.aiuta.fashionsdk.tryon.compose.data.internal.database.AiutaTryOnDatabase
import com.aiuta.fashionsdk.tryon.compose.data.internal.database.AiutaTryOnDatabaseFactory
import com.aiuta.fashionsdk.tryon.compose.data.internal.entity.local.time.TimestampEntity
import com.aiuta.fashionsdk.tryon.compose.data.internal.entity.local.time.toEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

internal class TimeDataSource(
    private val database: AiutaTryOnDatabase,
) {
    private val timeMetaQueries by lazy { database.timeMetaQueries }

    suspend fun insertOrUpdateTimestamp(
        key: String,
        timestamp: String,
    ) {
        withContext(Dispatchers.IO) {
            timeMetaQueries.insert(
                key = key,
                timestamp = timestamp,
            )
        }
    }

    suspend fun getTimestamp(key: String): TimestampEntity? = withContext(Dispatchers.IO) {
        timeMetaQueries.selectByKey(key).executeAsOneOrNull()?.toEntity()
    }

    suspend fun deleteTimestamp(key: String) {
        withContext(Dispatchers.IO) {
            timeMetaQueries.deleteByKey(key)
        }
    }

    companion object {
        fun getInstance(): TimeDataSource = TimeDataSource(
            database = AiutaTryOnDatabaseFactory.getInstance(),
        )
    }
}
