package com.aiuta.fashionsdk.tryon.compose.ui.internal.screens.selector.analytic

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.aiuta.fashionsdk.internal.analytic.model.AiutaAnalyticsTryOnEvent
import com.aiuta.fashionsdk.internal.analytic.model.AiutaAnalyticsTryOnEventType
import com.aiuta.fashionsdk.internal.analytic.model.OpenMainScreen
import com.aiuta.fashionsdk.internal.analytic.model.TapChangePhoto
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.images.isNotEmpty
import com.aiuta.fashionsdk.tryon.compose.domain.models.internal.generated.images.size
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.FashionTryOnController
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAnalytic
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalController

@Composable
internal fun sendOpenMainScreenEvent() {
    val analytic = LocalAnalytic.current
    val controller = LocalController.current

    LaunchedEffect(Unit) {
        analytic.sendEvent(
            event =
                OpenMainScreen(
                    lastPhotoSelection = controller.lastSavedImages.value.size.toString(),
                ),
        )
    }
}

internal fun FashionTryOnController.sendTapChangePhotoEvent(
    isHistorySheetOpened: Boolean = false,
) {
    analytic.sendEvent(
        event =
            TapChangePhoto(
                hasCurrentPhotos = lastSavedImages.value.isNotEmpty().toString(),
                hasHistoryPhotos = isHistorySheetOpened.toString(),
            ),
    )
}

internal fun FashionTryOnController.sendStartUITryOnEvent() {
    analytic.sendEvent(
        event =
            AiutaAnalyticsTryOnEvent(
                event = AiutaAnalyticsTryOnEventType.TRY_ON_STARTED,
            ),
    )
}
