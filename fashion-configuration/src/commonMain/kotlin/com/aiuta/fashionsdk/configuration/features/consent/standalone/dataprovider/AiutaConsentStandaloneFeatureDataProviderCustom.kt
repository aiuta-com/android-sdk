package com.aiuta.fashionsdk.configuration.features.consent.standalone.dataprovider

import kotlinx.coroutines.flow.StateFlow

/**
 * Interface for custom standalone consent feature data providers.
 *
 * This interface allows for custom implementations of consent data management,
 * providing methods to track and obtain user consents.
 *
 * @property obtainedConsentsIds Flow of consent IDs that have been obtained
 */
public interface AiutaConsentStandaloneFeatureDataProviderCustom : AiutaConsentStandaloneFeatureDataProvider {
    public val obtainedConsentsIds: StateFlow<List<String>>

    /**
     * Marks the specified consents as obtained by the user.
     *
     * @param consentIds List of consent identifiers to mark as obtained
     */
    public suspend fun obtainConsent(consentIds: List<String>)
}
