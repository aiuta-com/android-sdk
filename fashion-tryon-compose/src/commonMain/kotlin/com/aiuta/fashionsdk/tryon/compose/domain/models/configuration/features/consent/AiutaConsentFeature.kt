package com.aiuta.fashionsdk.tryon.compose.domain.models.configuration.features.consent

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import com.aiuta.fashionsdk.tryon.compose.domain.models.configuration.features.consent.builtin.strings.AiutaBuiltInWithOnboardingStrings
import com.aiuta.fashionsdk.tryon.compose.domain.models.configuration.features.consent.standalone.dataprovider.AiutaStandaloneOnboardingPageDataProvider
import com.aiuta.fashionsdk.tryon.compose.domain.models.configuration.features.consent.standalone.strings.AiutaStandaloneOnboardingPageStrings
import com.aiuta.fashionsdk.tryon.compose.ui.internal.controller.composition.LocalAiutaConfiguration
import com.aiuta.fashionsdk.tryon.compose.ui.internal.utils.features.checkFeatureAvailability

public interface AiutaConsentFeature {
    public class BuiltInWithOnboarding(
        public val strings: AiutaBuiltInWithOnboardingStrings,
    ) : AiutaConsentFeature

    public class StandaloneOnboardingPage(
        public val strings: AiutaStandaloneOnboardingPageStrings,
        public val dataProvider: AiutaStandaloneOnboardingPageDataProvider? = null,
    ) : AiutaConsentFeature
}

@Composable
@ReadOnlyComposable
internal fun consentFeature(): AiutaConsentFeature? {
    val aiutaConfiguration = LocalAiutaConfiguration.current
    return aiutaConfiguration.features.consent
}

@Composable
@ReadOnlyComposable
internal fun consentBuiltInFeature(): AiutaConsentFeature.BuiltInWithOnboarding? {
    return consentFeature() as? AiutaConsentFeature.BuiltInWithOnboarding
}

@Composable
@ReadOnlyComposable
internal fun consentStandaloneFeature(): AiutaConsentFeature.StandaloneOnboardingPage? {
    return consentFeature() as? AiutaConsentFeature.StandaloneOnboardingPage
}

@Composable
@ReadOnlyComposable
internal fun strictConsentStandaloneFeature(): AiutaConsentFeature.StandaloneOnboardingPage {
    return checkFeatureAvailability(
        name = "AiutaConsentFeature.StandaloneOnboardingPage",
        feature = consentStandaloneFeature(),
    )
}
