package com.aiuta.fashionsdk.compose.tokens.gradient

import androidx.compose.ui.graphics.Color
import com.aiuta.fashionsdk.compose.tokens.color.DefaultAiutaColors

public fun aiutaGradients(
    loadingAnimationGradient: List<Color> =
        listOf(
            DefaultAiutaColors.brand,
            Color.Transparent,
        ),
): AiutaGradients {
    return AiutaGradients(
        loadingAnimationGradient = loadingAnimationGradient,
    )
}
