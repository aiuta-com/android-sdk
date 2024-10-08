package com.aiuta.fashionsdk.compose.tokens.shape

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Creates an instance of [AiutaShapes] with customizable corner radii for various UI components.
 *
 * This function provides default values for the corner radii, which can be overridden by the host application.
 *
 * @return An instance of [AiutaShapes] with the specified or default corner radii.
 */
public fun aiutaShapes(
    mainImageRadiusDp: Dp = 24.dp,
    previewImageRadiusDp: Dp = 16.dp,
    onboardingImage: Dp = 8.dp,
    bottomSheetRadiusDp: Dp = 16.dp,
    buttonLRadiusDp: Dp = 8.dp,
    buttonMRadiusDp: Dp = 8.dp,
): AiutaShapes {
    return AiutaShapes(
        mainImage = RoundedCornerShape(mainImageRadiusDp),
        previewImage = RoundedCornerShape(previewImageRadiusDp),
        onboardingImage = RoundedCornerShape(onboardingImage),
        bottomSheet =
            RoundedCornerShape(
                topStart = bottomSheetRadiusDp,
                topEnd = bottomSheetRadiusDp,
            ),
        buttonL = RoundedCornerShape(buttonLRadiusDp),
        buttonM = RoundedCornerShape(buttonMRadiusDp),
    )
}
