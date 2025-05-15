package com.aiuta.fashionsdk.configuration.features.consent

import com.aiuta.fashionsdk.configuration.features.consent.standalone.data.AiutaConsentStandaloneFeatureData
import com.aiuta.fashionsdk.configuration.features.consent.standalone.dataprovider.AiutaConsentStandaloneFeatureDataProvider
import com.aiuta.fashionsdk.configuration.features.consent.standalone.icons.AiutaConsentStandaloneFeatureIcons
import com.aiuta.fashionsdk.configuration.features.consent.standalone.strings.AiutaConsentStandaloneFeatureStrings
import com.aiuta.fashionsdk.configuration.features.consent.standalone.styles.AiutaConsentStandaloneFeatureStyles

public interface AiutaConsentStandaloneFeature : AiutaConsentFeature {
    public val strings: AiutaConsentStandaloneFeatureStrings
    public val data: AiutaConsentStandaloneFeatureData
    public val dataProvider: AiutaConsentStandaloneFeatureDataProvider
    public val icons: AiutaConsentStandaloneFeatureIcons
    public val styles: AiutaConsentStandaloneFeatureStyles
}
