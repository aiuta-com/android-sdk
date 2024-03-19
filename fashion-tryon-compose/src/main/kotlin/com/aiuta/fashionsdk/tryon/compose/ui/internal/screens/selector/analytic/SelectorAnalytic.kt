package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.selector.analytic

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.aiuta.fashionsdk.analytic.model.OpenMainScreen
import com.aiuta.fashionsdk.analytic.model.TapChangePhoto
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
                value = controller.lastSavedPhotoUris.value.size.toString(),
            )
        }
    }
}

internal fun FashionTryOnController.sendTapChangePhotoEvent() {
    analytic.sendEvent(TapChangePhoto) {
        put(
            key = TapChangePhoto.HAS_CURRENT_PHOTOS_PARAM,
            value = lastSavedPhotoUris.value.isNotEmpty().toString(),
        )
        put(
            key = TapChangePhoto.HAS_HISTORY_PHOTOS_PARAM,
            value = false.toString(), // TODO Unmock with multi selector
        )
    }
}
