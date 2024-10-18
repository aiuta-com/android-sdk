package com.aiuta.fashionsdk.tryon.compose.ui.internal.sheets.operations.analytic

import com.aiuta.fashionsdk.internal.analytic.model.SelectOldPhotos
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.FashionTryOnController

internal fun FashionTryOnController.sendSelectOldPhotos(count: Int = 0) {
    analytic.sendEvent(
        event =
            SelectOldPhotos(
                count = count.toString(),
            ),
    )
}
