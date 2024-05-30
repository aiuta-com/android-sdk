package com.aiuta.fashionsdk.tryon.compose.data.internal.entity.code

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "aiuta_codes")
internal class AiutaCodeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val apiKey: String,
)
