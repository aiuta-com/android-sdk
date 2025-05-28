package com.aiuta.fashionsdk.configuration.features.tryon.disclaimer.strings

/**
 * Interface for disclaimer-related text strings.
 *
 * This interface defines the text strings used in the fit disclaimer UI,
 * allowing for localization and customization of user-facing text.
 *
 * @property tryOnFitTitle Title text for the fit disclaimer
 * @property tryOnFitDescription Detailed description of fit disclaimer
 * @property tryOnFitButtonClose Text for the close button
 */
public interface AiutaTryOnFitDisclaimerFeatureStrings {
    public val tryOnFitTitle: String
    public val tryOnFitDescription: String
    public val tryOnFitButtonClose: String

    /**
     * Default implementation of [AiutaTryOnFitDisclaimerFeatureStrings].
     */
    public class Default : AiutaTryOnFitDisclaimerFeatureStrings {
        override val tryOnFitTitle: String = "Results may vary from real-life fit"
        override val tryOnFitDescription: String = "Virtual try-on is a visualization tool that shows how items might look and may not perfectly represent how the item will fit in reality"
        override val tryOnFitButtonClose: String = "Close"
    }
}
