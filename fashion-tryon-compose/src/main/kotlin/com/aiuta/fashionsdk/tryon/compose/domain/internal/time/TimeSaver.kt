package com.aiuta.fashionsdk.tryon.compose.domain.internal.time

import android.content.Context
import com.aiuta.fashionsdk.tryon.compose.data.internal.datasource.time.TimeDataSource
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

internal class TimeSaver(
    private val timeDataSource: TimeDataSource,
) {
    fun getCurrentTime(): LocalDateTime {
        return Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
    }

    suspend fun saveCurrentTime(key: String) {
        val currentTime = getCurrentTime()
        timeDataSource.insertOrUpdateTimestamp(
            key = key,
            timestamp = currentTime.toString(),
        )
    }

    suspend fun resetTime(key: String) {
        timeDataSource.deleteTimestamp(key = key)
    }

    suspend fun getLastSavedTime(key: String): LocalDateTime? {
        val lastSavedTime = timeDataSource.getTimestamp(key)?.timestamp
        return try {
            lastSavedTime?.let { LocalDateTime.parse(it) }
        } catch (e: Exception) {
            null
        }
    }

    companion object {
        fun getInstance(context: Context): TimeSaver {
            return TimeSaver(
                timeDataSource = TimeDataSource.getInstance(context),
            )
        }
    }
}
