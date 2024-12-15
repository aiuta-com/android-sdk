package com.aiuta.fashionsdk.tryon.compose.ui.internal.analytic

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.aiuta.fashionsdk.compose.tokens.AiutaTheme
import com.aiuta.fashionsdk.internal.analytic.model.Configure
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaConfiguration
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaTryOnDataController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAnalytic
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.data.providePoweredByUrl

// Configure
@Composable
internal fun sendConfigureEvent(theme: AiutaTheme?) {
    val analytic = LocalAnalytic.current
    val configuration = LocalAiutaConfiguration.current
    val dataController = LocalAiutaTryOnDataController.current

    LaunchedEffect(Unit) {
        val isPoweredByVisible = dataController.providePoweredByUrl() != null

        analytic.sendEvent(
            event =
                Configure(
                    hasCustomConfiguration = (theme != null).toString(),
                    photoSelectionLimit = "1",
                    isWatermarkProvided = (theme?.watermark != null).toString(),
                    isHistoryEnable = configuration.isHistoryAvailable.toString(),
                    isPoweredByVisible = isPoweredByVisible.toString(),
                ),
        )
    }
}
