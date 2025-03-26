package com.aiuta.fashionsdk.tryon.compose.data.internal.entity.local.code

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "aiuta_codes")
internal class AiutaCodeEntity(
    @PrimaryKey
    val subscriptionId: String,
)
