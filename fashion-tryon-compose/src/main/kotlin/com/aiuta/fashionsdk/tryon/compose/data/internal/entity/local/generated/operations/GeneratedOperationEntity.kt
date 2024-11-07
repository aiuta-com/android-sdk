package com.aiuta.fashionsdk.tryon.compose.data.internal.entity.local.generated.operations

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "generated_operation")
internal class GeneratedOperationEntity(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
)
