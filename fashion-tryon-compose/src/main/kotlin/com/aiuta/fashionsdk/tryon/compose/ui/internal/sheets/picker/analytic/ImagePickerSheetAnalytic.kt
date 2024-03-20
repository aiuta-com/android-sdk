package com.aiuta.fashionsdk.tryon.compose.ui.internal.sheets.picker.analytic

import com.aiuta.fashionsdk.analytic.model.SelectNewPhotos
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.FashionTryOnController

internal fun FashionTryOnController.sendSelectNewPhotosEvent(
    fromCamera: Int = 0,
    fromGallery: Int = 0,
) {
    analytic.sendEvent(SelectNewPhotos) {
        put(
            key = SelectNewPhotos.FROM_CAMERA_PARAM,
            value = fromCamera.toString(),
        )
        put(
            key = SelectNewPhotos.FROM_GALLERY_PARAM,
            value = fromGallery.toString(),
        )
    }
}
