package com.aiuta.fashionsdk.tryon.compose.data.internal.entity.local.onboarding

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "onboarding_consents")
internal class ConsentEntity(
    @PrimaryKey
    val id: String,
    val isObtained: Boolean,
)
