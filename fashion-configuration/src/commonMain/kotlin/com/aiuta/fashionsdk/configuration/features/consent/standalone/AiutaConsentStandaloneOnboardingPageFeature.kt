package com.aiuta.fashionsdk.configuration.features.consent

import com.aiuta.fashionsdk.configuration.features.AiutaFeature
import com.aiuta.fashionsdk.configuration.features.consent.AiutaConsentFeature
import com.aiuta.fashionsdk.configuration.features.consent.standalone.data.AiutaConsentStandaloneOnboardingPageFeatureData
import com.aiuta.fashionsdk.configuration.features.consent.standalone.dataprovider.AiutaConsentStandaloneOnboardingPageFeatureDataProvider
import com.aiuta.fashionsdk.configuration.features.consent.standalone.strings.AiutaConsentStandaloneOnboardingPageFeatureStrings
import com.aiuta.fashionsdk.configuration.internal.utils.checkNotNullWithDescription

public class AiutaConsentStandaloneOnboardingPageFeature private constructor(
    public override val strings: AiutaConsentStandaloneOnboardingPageFeatureStrings,
    public override val data: AiutaConsentStandaloneOnboardingPageFeatureData,
    public override val dataProvider: AiutaConsentStandaloneOnboardingPageFeatureDataProvider?,
) : AiutaConsentStandaloneFeature {

    public class Builder : AiutaFeature.Builder {
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
