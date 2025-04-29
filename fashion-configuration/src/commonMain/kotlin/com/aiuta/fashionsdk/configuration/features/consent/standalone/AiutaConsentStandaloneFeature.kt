package com.aiuta.fashionsdk.configuration.features.consent

import com.aiuta.fashionsdk.configuration.features.consent.standalone.data.AiutaConsentStandaloneOnboardingPageFeatureData
import com.aiuta.fashionsdk.configuration.features.consent.standalone.dataprovider.AiutaConsentStandaloneOnboardingPageFeatureDataProvider
import com.aiuta.fashionsdk.configuration.features.consent.standalone.strings.AiutaConsentStandaloneOnboardingPageFeatureStrings

public interface AiutaConsentStandaloneFeature : AiutaConsentFeature {
    public val strings: AiutaConsentStandaloneOnboardingPageFeatureStrings
    public val data: AiutaConsentStandaloneOnboardingPageFeatureData
    public val dataProvider: AiutaConsentStandaloneOnboardingPageFeatureDataProvider?
}
