package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.selector.analytic

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.aiuta.fashionsdk.analytic.model.OpenMainScreen
import com.aiuta.fashionsdk.analytic.model.StartUITryOn
import com.aiuta.fashionsdk.analytic.model.TapChangePhoto
import com.aiuta.fashionsdk.tryon.compose.domain.models.isNotEmpty
import com.aiuta.fashionsdk.tryon.compose.domain.models.size
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.FashionTryOnController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.LocalAnalytic
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.LocalController

@Composable
internal fun sendOpenMainScreenEvent() {
    val analytic = LocalAnalytic.current
    val controller = LocalController.current

    LaunchedEffect(Unit) {
        analytic.sendEvent(OpenMainScreen) {
            put(
                key = OpenMainScreen.LAST_PHOTO_SELECTION_PARAM,
                value = controller.lastSavedImages.value.size.toString(),
            )
        }
    }
}

internal fun FashionTryOnController.sendTapChangePhotoEvent(
    isHistorySheetOpened: Boolean = false,
) {
    analytic.sendEvent(TapChangePhoto) {
        put(
            key = TapChangePhoto.HAS_CURRENT_PHOTOS_PARAM,
            value = lastSavedImages.value.isNotEmpty().toString(),
        )
        put(
            key = TapChangePhoto.HAS_HISTORY_PHOTOS_PARAM,
            value = isHistorySheetOpened.toString(),
        )
    }
}

internal fun FashionTryOnController.sendStartUITryOnEvent(origin: StartUITryOn.Origin) {
    analytic.sendEvent(StartUITryOn) {
        put(
            key = StartUITryOn.ORIGIN_PARAM,
            value = origin.value,
        )
        put(
            key = StartUITryOn.SKU_ID_PARAM,
            value = activeSKUItem.value.skuId,
        )
        put(
            key = StartUITryOn.SKU_CATALOG_NAME_PARAM,
            value = activeSKUItem.value.catalogName,
        )
        put(
            key = StartUITryOn.PHOTOS_COUNT_PARAM,
            value = lastSavedImages.value.size.toString(),
        )
    }
}
