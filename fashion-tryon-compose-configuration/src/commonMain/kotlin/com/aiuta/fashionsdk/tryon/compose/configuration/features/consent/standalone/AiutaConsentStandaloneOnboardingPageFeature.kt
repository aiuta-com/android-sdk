package com.aiuta.fashionsdk.tryon.compose.configuration.features.consent.standalone

import com.aiuta.fashionsdk.tryon.compose.configuration.features.AiutaTryOnConfigurationFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.consent.AiutaConsentFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.consent.standalone.data.AiutaConsentStandaloneOnboardingPageFeatureData
import com.aiuta.fashionsdk.tryon.compose.configuration.features.consent.standalone.dataprovider.AiutaConsentStandaloneOnboardingPageFeatureDataProvider
import com.aiuta.fashionsdk.tryon.compose.configuration.features.consent.standalone.strings.AiutaConsentStandaloneOnboardingPageFeatureStrings
import com.aiuta.fashionsdk.tryon.compose.configuration.internal.utils.checkNotNullWithDescription

public class AiutaConsentStandaloneOnboardingPageFeature private constructor(
    public val strings: AiutaConsentStandaloneOnboardingPageFeatureStrings,
    public val data: AiutaConsentStandaloneOnboardingPageFeatureData,
    public val dataProvider: AiutaConsentStandaloneOnboardingPageFeatureDataProvider?,
) : AiutaConsentFeature {

    public class Builder : AiutaTryOnConfigurationFeature.Builder {
        public var strings: AiutaConsentStandaloneOnboardingPageFeatureStrings? = null
        public var data: AiutaConsentStandaloneOnboardingPageFeatureData? = null
        public var dataProvider: AiutaConsentStandaloneOnboardingPageFeatureDataProvider? = null

        public override fun build(): AiutaConsentStandaloneOnboardingPageFeature {
            val parentClass = "AiutaConsentStandaloneOnboardingPageFeature"

            return AiutaConsentStandaloneOnboardingPageFeature(
                strings = strings.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "strings",
                ),
                data = data.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "data",
                ),
                dataProvider = this.dataProvider,
            )
        }
    }
}
