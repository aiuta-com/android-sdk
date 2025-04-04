package com.aiuta.fashionsdk.tryon.compose.configuration.features.tryon.styles

import androidx.compose.ui.graphics.Color

public interface AiutaTryOnFeatureStyles {
    public val tryOnButtonGradient: List<Color>?

    public class Default : AiutaTryOnFeatureStyles {
        override val tryOnButtonGradient: List<Color>? = null
    }
}
