package com.aiuta.fashionsdk.configuration.defaults.icons.features.onboarding.bestresult

import com.aiuta.fashion_configuration_defaults_icons.generated.resources.Res
import com.aiuta.fashion_configuration_defaults_icons.generated.resources.ic_onboarding_best_results_bad_24
import com.aiuta.fashion_configuration_defaults_icons.generated.resources.ic_onboarding_best_results_good_24
import com.aiuta.fashionsdk.compose.resources.drawable.AiutaComposeDrawableResource
import com.aiuta.fashionsdk.compose.resources.drawable.AiutaIcon
import com.aiuta.fashionsdk.configuration.features.onboarding.bestresult.icons.AiutaOnboardingBestResultsPageFeatureIcons

public class DefaultAiutaOnboardingBestResultsPageFeatureIcons : AiutaOnboardingBestResultsPageFeatureIcons {
    override val onboardingBestResultsGood24: AiutaIcon = AiutaIcon(
        iconResource = AiutaComposeDrawableResource(Res.drawable.ic_onboarding_best_results_good_24),
    )
    override val onboardingBestResultsBad24: AiutaIcon = AiutaIcon(
        iconResource = AiutaComposeDrawableResource(Res.drawable.ic_onboarding_best_results_bad_24),
    )
}
