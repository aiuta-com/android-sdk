package com.aiuta.fashionsdk.tryon.compose.configuration.features.consent.standalone

import com.aiuta.fashionsdk.tryon.compose.configuration.features.AiutaFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.consent.AiutaConsentFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.consent.standalone.dataprovider.AiutaConsentStandaloneOnboardingPageDataProvider
import com.aiuta.fashionsdk.tryon.compose.configuration.features.consent.standalone.strings.AiutaConsentStandaloneOnboardingPageStrings
import com.aiuta.fashionsdk.tryon.compose.configuration.utils.checkNotNullWithDescription

public class AiutaConsentStandaloneOnboardingPage private constructor(
    public val strings: AiutaConsentStandaloneOnboardingPageStrings,
    public val dataProvider: AiutaConsentStandaloneOnboardingPageDataProvider?,
) : AiutaConsentFeature {

    public class Builder : AiutaFeature.Builder {
        public var strings: AiutaConsentStandaloneOnboardingPageStrings? = null
        public var dataProvider: AiutaConsentStandaloneOnboardingPageDataProvider? = null

        public override fun build(): AiutaConsentStandaloneOnboardingPage {
            val parentClass = "AiutaConsentStandaloneOnboardingPage"

            return AiutaConsentStandaloneOnboardingPage(
                strings = strings.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "strings",
                ),
                dataProvider = this.dataProvider,
            )
        }
    }
}
