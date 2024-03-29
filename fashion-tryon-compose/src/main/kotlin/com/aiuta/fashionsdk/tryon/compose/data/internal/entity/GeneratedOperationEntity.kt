package com.aiuta.fashionsdk.tryon.compose.data.internal.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "generated_operation")
internal class GeneratedOperationEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
)
