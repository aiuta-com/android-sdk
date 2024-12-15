package com.aiuta.fashionsdk.tryon.compose.ui.internal.controller

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.aiuta.fashionsdk.tryon.compose.domain.models.configuration.AiutaTryOnConfiguration
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.sku.SKUGenerationUIStatus
import com.aiuta.fashionsdk.tryon.compose.ui.internal.navigation.NavigationScreen
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
internal fun FashionTryOnController.generationNavigationListener() {
    // We should clear generation status and list of operation
    // every time, when move back from result to selector screen,
    // therefore let's clear it on each navigation to NavigationScreen.ImageSelector
    LaunchedEffect(currentScreen.value) {
        if (currentScreen.value == NavigationScreen.ImageSelector) {
            if (!isGenerationActive.value) {
                generationStatus.value = SKUGenerationUIStatus.IDLE
            }
            generationOperations.clear()
        }
    }
}

@Composable
internal fun FashionTryOnController.historyAvailabilityListener(
    configuration: AiutaTryOnConfiguration,
) {
    // We should delete all generations, if history not available
    LaunchedEffect(Unit) {
        if (!configuration.toggles.isHistoryAvailable) {
            generatedImageInteractor.removeAll()
        }
    }
}

internal fun FashionTryOnController.updationActiveSKUItemListener() {
    // Observe external changes of current sku
    aiutaTryOnListeners
        .updatedActiveSKUItem
        .filterNotNull()
        .onEach { updatedSKUItem ->
            changeActiveSKU(updatedSKUItem)
        }
        .launchIn(generalScope)
}
