package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.controller

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.aiuta.fashionsdk.analytic.model.UpdateResultsScreen
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.LocalAnalytic
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.LocalController
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
            analytic.sendEvent(UpdateResultsScreen) {
                put(
                    key = UpdateResultsScreen.SKU_ID_PARAM,
                    value = activeSKUItem.skuId,
                )
                put(
                    key = UpdateResultsScreen.SKU_CATALOG_NAME_PARAM,
                    value = activeSKUItem.catalogName,
                )
                put(
                    key = UpdateResultsScreen.PHOTOS_IN_PROGRESS_PARAM,
                    value = loadingOperations.value.size.toString(),
                )
                put(
                    key = UpdateResultsScreen.GENERATED_PHOTOS_PARAM,
                    value = successOperations.value.flatMap { it.generatedImageUrls }.size.toString(),
                )
            }
        }
    }
}
