package com.aiuta.fashionsdk.compose.tokens.gradient

import androidx.compose.ui.graphics.Color
import com.aiuta.fashionsdk.compose.tokens.color.DefaultAiutaColors

/**
 * Creates an instance of [AiutaGradients]
 *
 * @return An instance of [AiutaGradients] with the specified or default loading animation gradient.
 * @see AiutaGradients
 * @see DefaultAiutaColors
 */
public fun aiutaGradients(
    loadingAnimationGradient: List<Color> =
        listOf(
            DefaultAiutaColors.brand,
            Color.Transparent,
        ),
    tryOnButtonBackground: List<Color> = emptyList(),
): AiutaGradients {
    return AiutaGradients(
        loadingAnimation = loadingAnimationGradient,
        tryOnButtonBackground = tryOnButtonBackground,
    )
}
