package com.aiuta.fashionsdk.tryon.compose.ui.internal.controller

import com.aiuta.fashionsdk.Aiuta
import com.aiuta.fashionsdk.tryon.compose.data.internal.database.AppDatabase

internal suspend fun validateControllerCache(aiuta: Aiuta) {
    try {
        AppDatabase.validateCache(
            aiuta = aiuta,
        )
    } catch (e: Exception) {
        // Failed to validate cache, use saved
    }
}
