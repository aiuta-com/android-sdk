package com.aiuta.fashionsdk.tryon.compose.ui.internal.controller

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.aiuta.fashionsdk.Aiuta
import com.aiuta.fashionsdk.tryon.compose.data.internal.database.AppDatabase

@Composable
internal fun validateControllerCache(aiuta: () -> Aiuta) {
    LaunchedEffect(Unit) {
        try {
            AppDatabase.validateCache(
                aiuta = aiuta(),
            )
        } catch (e: Exception) {
            // Failed to validate cache, use saved
        }
    }
}
