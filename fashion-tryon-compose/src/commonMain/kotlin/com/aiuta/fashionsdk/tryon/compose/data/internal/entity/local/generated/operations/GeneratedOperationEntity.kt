package com.aiuta.fashionsdk.tryon.compose.data.internal.entity.local.generated.operations

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
@Entity(tableName = "generated_operation")
internal class GeneratedOperationEntity(
    @PrimaryKey
    val id: String = Uuid.random().toString(),
)
