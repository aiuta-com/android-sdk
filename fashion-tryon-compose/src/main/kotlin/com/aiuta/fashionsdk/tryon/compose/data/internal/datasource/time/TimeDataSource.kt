package com.aiuta.fashionsdk.tryon.compose.data.internal.datasource.time

import android.content.Context
import com.aiuta.fashionsdk.tryon.compose.data.internal.database.AppDatabase
import com.aiuta.fashionsdk.tryon.compose.data.internal.datasource.time.dao.TimeDao
import com.aiuta.fashionsdk.tryon.compose.data.internal.entity.local.time.TimestampEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class TimeDataSource(
    private val timeDao: TimeDao,
) {
    suspend fun insertOrUpdateTimestamp(
        key: String,
        timestamp: String,
    ) {
        withContext(Dispatchers.IO) {
            timeDao.insert(
                timestamp =
                    TimestampEntity(
                        timestampKey = key,
                        timestamp = timestamp,
                    ),
            )
        }
    }

    suspend fun getTimestamp(key: String): TimestampEntity? {
        return withContext(Dispatchers.IO) {
            timeDao
                .getTimestamps(
                    limit = 1,
                    key = key,
                )
                .firstOrNull()
        }
    }

    suspend fun deleteTimestamp(key: String) {
        withContext(Dispatchers.IO) {
            timeDao.deleteTimestamp(key = key)
        }
    }

    companion object {
        fun getInstance(context: Context): TimeDataSource {
            return TimeDataSource(
                timeDao = AppDatabase.getInstance(context).timeDao(),
            )
        }
    }
}
