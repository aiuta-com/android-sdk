package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.selector.utils

import androidx.core.net.toUri
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.FashionTryOnController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.activateGeneration
import com.aiuta.fashionsdk.tryon.core.domain.models.SKUGenerationContainer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

internal fun FashionTryOnController.startGeneration(scope: CoroutineScope) {
    scope.launch {
        try {
            activateGeneration()

            val imageUri = lastSavedPhotoUris.value.firstOrNull()?.toUri()
            val skuInfo = skuForGeneration()

            imageUri?.let {
                fashionTryOn().startSKUGeneration(
                    container =
                        SKUGenerationContainer(
                            fileUri = imageUri,
                            skuId = skuInfo.skuId,
                            skuCatalogName = skuInfo.catalogName,
                        ),
                )
            }
        } catch (e: Exception) {
            // TODO Fallback with error state
        }
    }
}
