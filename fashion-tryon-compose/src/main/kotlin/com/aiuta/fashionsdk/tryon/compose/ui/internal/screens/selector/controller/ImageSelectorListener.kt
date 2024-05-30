package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.selector.controller

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.sku.SKUGenerationUIStatus
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.deactivateGeneration
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.navigateTo
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.NavigationScreen

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
