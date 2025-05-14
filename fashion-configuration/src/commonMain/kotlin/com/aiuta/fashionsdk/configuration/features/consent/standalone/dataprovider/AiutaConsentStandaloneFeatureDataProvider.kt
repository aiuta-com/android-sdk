package com.aiuta.fashionsdk.configuration.features.consent.standalone.dataprovider

import kotlinx.coroutines.flow.StateFlow

public sealed interface AiutaConsentStandaloneFeatureDataProvider

public interface AiutaConsentStandaloneFeatureDataProviderLogic {
    public val obtainedConsentsIds: StateFlow<List<String>>
    public suspend fun obtainConsent(consentIds: List<String>)
}
