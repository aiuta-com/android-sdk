package com.aiuta.fashionsdk.configuration.features.consent.standalone.data

import com.aiuta.fashionsdk.configuration.features.consent.models.AiutaConsent

/**
 * Interface for standalone consent feature data.
 *
 * This interface defines the data model for consents presented in the standalone consent interface.
 *
 * @property consents List of consents to be displayed and managed
 */
public interface AiutaConsentStandaloneFeatureData {
    public val consents: List<AiutaConsent>
}
