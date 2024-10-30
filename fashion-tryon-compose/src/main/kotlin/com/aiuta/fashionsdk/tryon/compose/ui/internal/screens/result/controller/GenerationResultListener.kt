package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.controller

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.aiuta.fashionsdk.internal.analytic.model.UpdateResultsScreen
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAnalytic
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.navigateBack
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.subscribeToLoadingOperations
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.subscribeToSuccessOperations

@Composable
internal fun GenerationResultListener() {
    GenerationResultUpdateScreenListener()

    GenerationResultSessionListener()
}

@Composable
private fun GenerationResultUpdateScreenListener() {
    val analytic = LocalAnalytic.current
    val controller = LocalController.current

    val activeSKUItem = controller.activeSKUItem.value
    val successOperations = controller.subscribeToSuccessOperations()
    val loadingOperations = controller.subscribeToLoadingOperations()

    // Should notify about new generated images
    LaunchedEffect(successOperations.value, loadingOperations.value) {
        if (successOperations.value.isNotEmpty() || loadingOperations.value.isNotEmpty()) {
            analytic.sendEvent(
                event =
                    UpdateResultsScreen(
                        skuId = activeSKUItem.skuId,
                        skuCatalogName = activeSKUItem.catalogName,
                        photosInProgress = loadingOperations.value.size.toString(),
                        generatedPhotos = successOperations.value.flatMap { it.generatedImageUrls }.size.toString(),
                    ),
            )
        }
    }
}

@Composable
private fun GenerationResultSessionListener() {
    val controller = LocalController.current

    // If we delete all session images, we should navigate back to picker
    LaunchedEffect(controller.sessionGenerationInteractor.sessionGenerationsUrls.size) {
        if (controller.sessionGenerationInteractor.sessionGenerationsUrls.isEmpty()) {
            controller.navigateBack()
        }
    }
}
