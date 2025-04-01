package com.aiuta.fashionsdk.tryon.compose.configuration.features.consent.standalone

import com.aiuta.fashionsdk.annotations.AiutaDsl
import com.aiuta.fashionsdk.tryon.compose.configuration.features.consent.AiutaConsentFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.consent.standalone.dataprovider.AiutaConsentStandaloneOnboardingPageDataProvider
import com.aiuta.fashionsdk.tryon.compose.configuration.features.consent.standalone.strings.AiutaConsentStandaloneOnboardingPageStrings
import com.aiuta.fashionsdk.tryon.compose.configuration.utils.checkNotNullWithDescription

public class AiutaConsentStandaloneOnboardingPage(
    public val strings: AiutaConsentStandaloneOnboardingPageStrings,
    public val dataProvider: AiutaConsentStandaloneOnboardingPageDataProvider? = null,
) : AiutaConsentFeature {
    @AiutaDsl
    public class Builder : AiutaConsentFeature.Builder {
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
