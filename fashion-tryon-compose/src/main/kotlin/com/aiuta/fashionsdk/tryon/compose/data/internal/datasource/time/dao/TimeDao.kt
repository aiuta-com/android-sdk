package com.aiuta.fashionsdk.tryon.compose.data.internal.datasource.time.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aiuta.fashionsdk.tryon.compose.data.internal.entity.local.time.TimestampEntity

@Dao
internal interface TimeDao {
    @Query("SELECT * from time_stamps WHERE timestampKey = :key LIMIT :limit")
    suspend fun getTimestamps(
        limit: Int,
        key: String,
    ): List<TimestampEntity>

    @Query("DELETE from time_stamps WHERE timestampKey = :key")
    suspend fun deleteTimestamp(key: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(timestamp: TimestampEntity)
}
