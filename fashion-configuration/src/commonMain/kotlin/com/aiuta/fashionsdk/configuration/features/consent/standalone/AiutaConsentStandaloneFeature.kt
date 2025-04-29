package com.aiuta.fashionsdk.configuration.features.consent

import com.aiuta.fashionsdk.configuration.features.consent.standalone.data.AiutaConsentStandaloneOnboardingPageFeatureData
import com.aiuta.fashionsdk.configuration.features.consent.standalone.dataprovider.AiutaConsentStandaloneOnboardingPageFeatureDataProvider
import com.aiuta.fashionsdk.configuration.features.consent.standalone.icons.AiutaConsentStandaloneFeatureIcons
import com.aiuta.fashionsdk.configuration.features.consent.standalone.strings.AiutaConsentStandaloneOnboardingPageFeatureStrings
import com.aiuta.fashionsdk.configuration.features.consent.standalone.styles.AiutaConsentStandaloneFeatureStyles

public interface AiutaConsentStandaloneFeature : AiutaConsentFeature {
    public val strings: AiutaConsentStandaloneOnboardingPageFeatureStrings
    public val data: AiutaConsentStandaloneOnboardingPageFeatureData
    public val dataProvider: AiutaConsentStandaloneOnboardingPageFeatureDataProvider?
    public val icons: AiutaConsentStandaloneFeatureIcons
    public val styles: AiutaConsentStandaloneFeatureStyles
}
