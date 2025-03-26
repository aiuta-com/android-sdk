package com.aiuta.fashionsdk.tryon.compose.ui.internal.controller

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.aiuta.fashionsdk.tryon.compose.domain.models.configuration.AiutaTryOnConfiguration
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.sku.SKUGenerationUIStatus
import com.aiuta.fashionsdk.tryon.compose.ui.internal.analytic.sendTerminateEvent
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

@Composable
internal fun FashionTryOnController.generationCancellationListener(
    configuration: AiutaTryOnConfiguration,
) {
    if (!configuration.toggles.isBackgroundExecutionAllowed) {
        val lifecycleOwner = LocalLifecycleOwner.current

        DisposableEffect(lifecycleOwner) {
            val observer =
                LifecycleEventObserver { _, event ->
                    if (event == Lifecycle.Event.ON_DESTROY) {
                        // Notify analytic about termination
                        if (generationStatus.value == SKUGenerationUIStatus.LOADING) {
                            sendTerminateEvent()
                        }

                        // Let's clean all generation processes
                        clean()
                    }
                }

            // Add the observer to the lifecycle
            lifecycleOwner.lifecycle.addObserver(observer)

            // When the effect leaves the Composition, remove the observer
            onDispose {
                lifecycleOwner.lifecycle.removeObserver(observer)
            }
        }
    }
}
