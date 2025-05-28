package com.aiuta.fashionsdk.configuration.features.consent.standalone.styles

/**
 * Interface for standalone consent feature styles.
 *
 * This interface defines the visual styles used in the standalone consent interface,
 * allowing for customization of the UI appearance.
 *
 * @property drawBordersAroundConsents Whether to draw borders around consent sections
 */
public interface AiutaConsentStandaloneFeatureStyles {
    public val drawBordersAroundConsents: Boolean

    /**
     * Default implementation of [AiutaConsentStandaloneFeatureStyles].
     *
     * Provides standard styling for the standalone consent feature,
     * with borders drawn around consent sections by default.
     */
    public class Default : AiutaConsentStandaloneFeatureStyles {
        override val drawBordersAroundConsents: Boolean = true
    }
}
