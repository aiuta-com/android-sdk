package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.selector.controller

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.sku.SKUGenerationUIStatus
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaConfiguration
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.deactivateGeneration
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.disableAutoTryOn
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.navigateTo
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.NavigationScreen
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.selector.utils.startGeneration

@Composable
internal fun ImageSelectorListener(enable: Boolean) {
    val controller = LocalController.current
    val generationStatus = controller.generationStatus.value

    LaunchedEffect(generationStatus) {
        if (generationStatus == SKUGenerationUIStatus.SUCCESS && enable) {
            controller.navigateTo(NavigationScreen.GENERATION_RESULT)
            controller.deactivateGeneration()
        }
    }
}

@Composable
internal fun ImageSelectorAutoTryOnListener() {
    val context = LocalContext.current
    val controller = LocalController.current
    val aiutaConfiguration = LocalAiutaConfiguration.current

    LaunchedEffect(controller.lastSavedImages.value) {
        if (controller.isAutoTryOnEnabled.value) {
            controller.disableAutoTryOn()
            controller.startGeneration(
                aiutaConfiguration = aiutaConfiguration,
                context = context,
            )
        }
    }
}
