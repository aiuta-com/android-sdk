package com.aiuta.fashionsdk.configuration.features.tryon.styles

import androidx.compose.ui.graphics.Color

/**
 * Interface for try-on feature styles.
 *
 * This interface defines the visual styles used in the try-on feature,
 * allowing for customization of the UI appearance.
 *
 * @property tryOnButtonGradient Optional list of colors for the try-on button gradient
 */
public interface AiutaTryOnFeatureStyles {
    public val tryOnButtonGradient: List<Color>?

    /**
     * Default implementation of [AiutaTryOnFeatureStyles].
     *
     * Provides standard styling for the try-on feature,
     * with no custom gradient for the try-on button.
     */
    public class Default : AiutaTryOnFeatureStyles {
        override val tryOnButtonGradient: List<Color>? = null
    }
}
