package com.aiuta.fashionsdk.configuration.features.consent.standalone.dataprovider

import kotlinx.coroutines.flow.StateFlow

public interface AiutaConsentStandaloneOnboardingPageFeatureDataProvider {
    public val obtainedConsentsIds: StateFlow<List<String>>
    public fun obtainConsent(consentIds: List<String>)
}
