package com.aiuta.fashionsdk.configuration.features.powerby.colors

import androidx.compose.ui.graphics.Color

public interface AiutaPoweredByFeatureColors {
    public val aiuta: Color

    public class Default : AiutaPoweredByFeatureColors {
        // TODO Support on UI
        override val aiuta: Color = Color(0xFF4000FF)
    }
}
