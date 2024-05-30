package com.aiuta.fashionsdk.tryon.compose.domain.internal.time

import android.content.Context
import com.aiuta.fashionsdk.tryon.compose.data.internal.datasource.time.TimeDataSource
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

internal class TimeSaver(
    private val timeDataSource: TimeDataSource,
) {
    private val formatter by lazy { SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH) }

    fun getCurrentTime(): Date {
        return Calendar.getInstance().time
    }

    suspend fun saveCurrentTime(key: String) {
        val currentTime = getCurrentTime()
        timeDataSource.insertOrUpdateTimestamp(
            key = key,
            timestamp = formatter.format(currentTime.time),
        )
    }

    suspend fun getLastSavedTime(key: String): Date? {
        val lastSavedTime = timeDataSource.getTimestamp(key)?.timestamp
        return try {
            lastSavedTime?.let { formatter.parse(it) }
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
