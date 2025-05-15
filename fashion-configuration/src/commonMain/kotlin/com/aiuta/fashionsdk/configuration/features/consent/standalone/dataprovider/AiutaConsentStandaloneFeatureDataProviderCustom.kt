package com.aiuta.fashionsdk.configuration.features.consent.standalone.dataprovider

import kotlinx.coroutines.flow.StateFlow

public interface AiutaConsentStandaloneFeatureDataProviderCustom : AiutaConsentStandaloneFeatureDataProvider {
    public val obtainedConsentsIds: StateFlow<List<String>>
    public suspend fun obtainConsent(consentIds: List<String>)
}
