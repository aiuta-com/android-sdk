package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.history.analytic

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.aiuta.fashionsdk.tryon.compose.ui.internal.analytic.sendOpenHistoryScreen
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalController

@Composable
internal fun sendOpenHistoryEvent() {
    val controller = LocalController.current

    LaunchedEffect(Unit) {
        controller.sendOpenHistoryScreen()
    }
}
