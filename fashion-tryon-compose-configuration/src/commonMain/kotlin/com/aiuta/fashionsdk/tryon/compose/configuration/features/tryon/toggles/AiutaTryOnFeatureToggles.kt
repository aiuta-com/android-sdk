package com.aiuta.fashionsdk.tryon.compose.configuration.features.tryon.toggles

public interface AiutaTryOnFeatureToggles {
    public val isBackgroundExecutionAllowed: Boolean

    public class Default : AiutaTryOnFeatureToggles {
        override val isBackgroundExecutionAllowed: Boolean = false
    }
}
