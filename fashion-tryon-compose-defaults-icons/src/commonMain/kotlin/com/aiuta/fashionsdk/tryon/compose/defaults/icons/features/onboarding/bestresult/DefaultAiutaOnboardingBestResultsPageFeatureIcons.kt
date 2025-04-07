package com.aiuta.fashionsdk.tryon.compose.defaults.icons.features.onboarding.bestresult

import com.aiuta.fashion_tryon_compose_defaults_icons.generated.resources.Res
import com.aiuta.fashion_tryon_compose_defaults_icons.generated.resources.ic_onboarding_best_results_bad_24
import com.aiuta.fashion_tryon_compose_defaults_icons.generated.resources.ic_onboarding_best_results_good_24
import com.aiuta.fashionsdk.tryon.compose.configuration.features.onboarding.bestresult.icons.AiutaOnboardingBestResultsPageFeatureIcons
import com.aiuta.fashionsdk.tryon.compose.resources.drawable.AiutaComposeDrawableResource
import com.aiuta.fashionsdk.tryon.compose.resources.drawable.AiutaIcon

public class DefaultAiutaOnboardingBestResultsPageFeatureIcons : AiutaOnboardingBestResultsPageFeatureIcons {
    override val onboardingBestResultsGood24: AiutaIcon = AiutaIcon(
        iconResource = AiutaComposeDrawableResource(Res.drawable.ic_onboarding_best_results_good_24),
    )
    override val onboardingBestResultsBad24: AiutaIcon = AiutaIcon(
        iconResource = AiutaComposeDrawableResource(Res.drawable.ic_onboarding_best_results_bad_24),
    )
}
