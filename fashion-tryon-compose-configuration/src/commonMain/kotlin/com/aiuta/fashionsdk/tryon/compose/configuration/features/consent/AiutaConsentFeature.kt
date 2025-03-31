package com.aiuta.fashionsdk.tryon.compose.configuration.features.consent

import com.aiuta.fashionsdk.tryon.compose.configuration.features.consent.builtin.strings.AiutaBuiltInWithOnboardingStrings
import com.aiuta.fashionsdk.tryon.compose.configuration.features.consent.standalone.dataprovider.AiutaStandaloneOnboardingPageDataProvider
import com.aiuta.fashionsdk.tryon.compose.configuration.features.consent.standalone.strings.AiutaStandaloneOnboardingPageStrings

public interface AiutaConsentFeature {
    public class BuiltInWithOnboarding(
        public val strings: AiutaBuiltInWithOnboardingStrings,
    ) : AiutaConsentFeature

    public class StandaloneOnboardingPage(
        public val strings: AiutaStandaloneOnboardingPageStrings,
        public val dataProvider: AiutaStandaloneOnboardingPageDataProvider? = null,
    ) : AiutaConsentFeature
}
