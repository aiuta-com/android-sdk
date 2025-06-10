package com.aiuta.fashionsdk.tryon.compose.data.internal.entity.local.time

import com.aiuta.fashionsdk.tryon.compose.data.internal.database.Time_meta

internal class TimestampEntity(
    val timestampKey: String,
    val timestamp: String,
)

internal fun Time_meta.toEntity(): TimestampEntity = TimestampEntity(
    timestampKey = key,
    timestamp = timestamp,
)
