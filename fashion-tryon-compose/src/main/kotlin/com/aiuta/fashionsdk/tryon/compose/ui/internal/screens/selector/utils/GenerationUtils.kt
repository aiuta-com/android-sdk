package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.selector.utils

import androidx.core.net.toUri
import com.aiuta.fashionsdk.analytic.model.StartUITryOn
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.FashionTryOnController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.activateGeneration
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.showErrorState
import com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.selector.analytic.sendStartUITryOnEvent
import com.aiuta.fashionsdk.tryon.core.domain.models.SKUGenerationContainer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

internal fun FashionTryOnController.startGeneration(
    origin: StartUITryOn.Origin,
    scope: CoroutineScope,
) {
    scope.launch {
        try {
            sendStartUITryOnEvent(origin)

            activateGeneration()

            val imageUri = lastSavedPhotoUris.value.firstOrNull()?.toUri()

            imageUri?.let {
                aiutaTryOn().startSKUGeneration(
                    container =
                        SKUGenerationContainer(
                            fileUri = imageUri,
                            skuId = activeSKUItem.value.skuId,
                            skuCatalogName = activeSKUItem.value.catalogName,
                        ),
                )
            }
        } catch (e: Exception) {
            showErrorState()
        }
    }
}
