package com.aiuta.fashionsdk.tryon.compose.data.internal.entity.local.time

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "time_stamps")
internal class TimestampEntity(
    @PrimaryKey
    val timestampKey: String,
    val timestamp: String,
)
