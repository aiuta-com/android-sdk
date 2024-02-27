package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.selector.controller

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.LocalController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.deactivateGeneration
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.navigateTo
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.NavigationScreen
import com.aiuta.fashionsdk.tryon.core.domain.models.SKUGenerationStatus

@Composable
internal fun ImageSelectorListener(enable: Boolean) {
    val controller = LocalController.current
    val skuGenerationStatus =
        controller
            .fashionTryOn()
            .skuGenerationStatus
            .collectAsStateWithLifecycle()

    LaunchedEffect(skuGenerationStatus.value) {
        if (skuGenerationStatus.value is SKUGenerationStatus.SuccessGenerationStatus && enable) {
            controller.navigateTo(NavigationScreen.GENERATION_RESULT)
            controller.deactivateGeneration()
        }
    }
}
