package com.aiuta.fashionsdk.configuration.features.consent.standalone.styles

public interface AiutaConsentStandaloneFeatureStyles {
    public val drawBordersAroundConsents: Boolean

    public class Default : AiutaConsentStandaloneFeatureStyles {
        override val drawBordersAroundConsents: Boolean = true
    }
}
