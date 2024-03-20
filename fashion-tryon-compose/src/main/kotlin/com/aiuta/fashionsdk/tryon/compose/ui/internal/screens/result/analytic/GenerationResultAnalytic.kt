package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.result.analytic

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.aiuta.fashionsdk.analytic.model.OpenResultsScreen
import com.aiuta.fashionsdk.analytic.model.SelectMoreToTryOn
import com.aiuta.fashionsdk.analytic.model.ViewGeneratedImage
import com.aiuta.fashionsdk.analytic.model.ViewMoreToTryOn
import com.aiuta.fashionsdk.tryon.compose.domain.models.SKUItem
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.FashionTryOnController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.LocalAnalytic
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.LocalController

@Composable
internal fun sendOpenResultsScreenEvent() {
    val analytic = LocalAnalytic.current
    val controller = LocalController.current
    val activeSKUItem = controller.activeSKUItem.value
    val skuGenerationStatus =
        controller
            .aiutaTryOn()
            .skuGenerationStatus
            .collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        analytic.sendEvent(OpenResultsScreen) {
            put(
                key = OpenResultsScreen.SKU_ID_PARAM,
                value = activeSKUItem.skuId,
            )
            put(
                key = OpenResultsScreen.SKU_CATALOG_NAME_PARAM,
                value = activeSKUItem.catalogName,
            )
            put(
                key = OpenResultsScreen.GENERATED_PHOTOS_PARAM,
                value = skuGenerationStatus.value.imageUrls.size.toString(),
            )
            put(
                key = OpenResultsScreen.PHOTOS_IN_PROGRESS_PARAM,
                value = "0", // TODO Unmock with multi select
            )
            put(
                key = OpenResultsScreen.MORE_TO_TRY_ON_PARAM,
                value = (activeSKUItem.generateMoreSKU?.size ?: 0).toString(),
            )
        }
    }
}

private var lastScrolledImageIndex = 0

internal fun FashionTryOnController.sendViewGeneratedImageEvent(
    newIndex: Int,
    type: ViewGeneratedImage.NavigationType,
) {
    if (lastScrolledImageIndex != newIndex) {
        lastScrolledImageIndex = newIndex
        val activeSKUItem = activeSKUItem.value

        analytic.sendEvent(ViewGeneratedImage) {
            put(
                key = ViewGeneratedImage.IMAGE_NUMBER_PARAM,
                value = newIndex.toString(),
            )
            put(
                key = ViewGeneratedImage.NAVIGATION_TYPE_PARAM,
                value = type.value,
            )
            put(
                key = ViewGeneratedImage.SKU_ID_PARAM,
                value = activeSKUItem.skuId,
            )
            put(
                key = ViewGeneratedImage.SKU_CATALOG_NAME_PARAM,
                value = activeSKUItem.catalogName,
            )
        }
    }
}

internal fun FashionTryOnController.sendViewMoreToTryOnEvent() {
    analytic.sendEvent(ViewMoreToTryOn)
}

internal fun FashionTryOnController.sendSelectMoreToTryOnEvent(skuItem: SKUItem) {
    analytic.sendEvent(SelectMoreToTryOn) {
        put(
            key = SelectMoreToTryOn.SKU_ID_PARAM,
            value = skuItem.skuId,
        )
        put(
            key = SelectMoreToTryOn.SKU_CATALOG_NAME_PARAM,
            value = skuItem.catalogName,
        )
    }
}
