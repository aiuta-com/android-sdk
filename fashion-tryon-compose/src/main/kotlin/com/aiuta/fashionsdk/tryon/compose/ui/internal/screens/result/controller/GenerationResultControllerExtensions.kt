package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.controller

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val DEFAULT_SHOW_DELAY = 3000L

internal fun GenerationResultController.showThanksFeedbackBlock() {
    scope.launch {
        isThanksFeedbackBlockVisible.value = true
        delay(DEFAULT_SHOW_DELAY)
        isThanksFeedbackBlockVisible.value = false
    }
}
