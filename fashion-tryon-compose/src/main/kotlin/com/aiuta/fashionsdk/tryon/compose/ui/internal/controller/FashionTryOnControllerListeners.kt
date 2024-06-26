package com.aiuta.fashionsdk.tryon.compose.ui.internal.controller

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.aiuta.fashionsdk.tryon.compose.domain.models.AiutaTryOnConfiguration
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.sku.SKUGenerationUIStatus
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.NavigationScreen

@Composable
internal fun FashionTryOnController.skuItemVisibilityListener() {
    LaunchedEffect(currentScreen.value) {
        isSKUItemVisible.value = currentScreen.value in screensWithSKUItems
    }
}

@Composable
internal fun FashionTryOnController.generationNavigationListener() {
    // We should clear generation status and list of operation
    // every time, when move back from result to selector screen,
    // therefore let's clear it on each navigation to NavigationScreen.IMAGE_SELECTOR
    LaunchedEffect(currentScreen.value) {
        if (currentScreen.value == NavigationScreen.IMAGE_SELECTOR) {
            generationStatus.value = SKUGenerationUIStatus.IDLE
            generationOperations.clear()
        }
    }
}

@Composable
internal fun FashionTryOnController.generationOperationListener() {
    // We should cancel current generation process, if we exit from
    // result screen back to selector
    LaunchedEffect(currentScreen.value) {
        if (currentScreen.value == NavigationScreen.IMAGE_SELECTOR) {
            cancelGenerationScope()
        }
    }
}

@Composable
internal fun FashionTryOnController.historyAvailabilityListener(
    configuration: AiutaTryOnConfiguration,
) {
    // We should delete all generations, if history not available
    LaunchedEffect(Unit) {
        if (!configuration.isHistoryAvailable) {
            generatedImageInteractor.removeAll()
        }
    }
}
