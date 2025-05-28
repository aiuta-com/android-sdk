package com.aiuta.fashionsdk.configuration.features.tryon.toggles

/**
 * Interface for try-on feature toggles.
 * 
 * This interface defines the feature flags and toggles that control
 * various behaviors and capabilities of the try-on feature.
 * 
 * @property isBackgroundExecutionAllowed Whether the try-on feature can execute operations in the background
 */
public interface AiutaTryOnFeatureToggles {
    public val isBackgroundExecutionAllowed: Boolean

    /**
     * Default implementation of [AiutaTryOnFeatureToggles].
     * 
     * Provides standard toggle values for the try-on feature,
     * with background execution disabled by default.
     */
    public class Default : AiutaTryOnFeatureToggles {
        override val isBackgroundExecutionAllowed: Boolean = false
    }
}
