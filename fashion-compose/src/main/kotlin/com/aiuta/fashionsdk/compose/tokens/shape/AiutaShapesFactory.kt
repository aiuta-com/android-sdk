package com.aiuta.fashionsdk.compose.tokens.shape

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

// TODO Add docs
public fun aiutaShapes(
    mainImageRadiusDp: Dp = 24.dp,
    previewImageRadiusDp: Dp = 16.dp,
    historyImageRadiusDp: Dp = 24.dp,
    bottomSheetRadiusDp: Dp = 24.dp,
    buttonLRadiusDp: Dp = 8.dp,
    buttonMRadiusDp: Dp = 8.dp,
): AiutaShapes {
    return AiutaShapes(
        mainImageShape = RoundedCornerShape(mainImageRadiusDp),
        previewImageShape = RoundedCornerShape(previewImageRadiusDp),
        historyImageShape = RoundedCornerShape(historyImageRadiusDp),
        bottomSheetShape =
            RoundedCornerShape(
                topStart = bottomSheetRadiusDp,
                topEnd = bottomSheetRadiusDp,
            ),
        buttonLShape = RoundedCornerShape(buttonLRadiusDp),
        buttonMShape = RoundedCornerShape(buttonMRadiusDp),
    )
}
