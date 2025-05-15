package com.aiuta.fashionsdk.tryon.compose.data.internal.entity.local.consent

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "obtained_consents")
internal class ObtainedConsentEntity(
    @PrimaryKey
    val id: String,
)
