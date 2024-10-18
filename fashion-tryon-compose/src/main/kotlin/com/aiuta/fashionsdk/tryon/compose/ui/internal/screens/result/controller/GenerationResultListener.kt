package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.controller

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.aiuta.fashionsdk.internal.analytic.model.UpdateResultsScreen
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAnalytic
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.subscribeToLoadingOperations
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.subscribeToSuccessOperations

@Composable
internal fun GenerationResultListener() {
    val analytic = LocalAnalytic.current
    val controller = LocalController.current

    val activeSKUItem = controller.activeSKUItem.value
    val successOperations = controller.subscribeToSuccessOperations()
    val loadingOperations = controller.subscribeToLoadingOperations()

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
