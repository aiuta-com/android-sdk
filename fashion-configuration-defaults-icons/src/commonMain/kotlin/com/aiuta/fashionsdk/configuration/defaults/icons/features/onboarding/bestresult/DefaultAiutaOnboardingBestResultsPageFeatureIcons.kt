package com.aiuta.fashionsdk.configuration.defaults.icons.features.onboarding.bestresult

import com.aiuta.fashion_configuration_defaults_icons.generated.resources.Res
import com.aiuta.fashion_configuration_defaults_icons.generated.resources.ic_onboarding_best_results_bad_24
import com.aiuta.fashion_configuration_defaults_icons.generated.resources.ic_onboarding_best_results_good_24
import com.aiuta.fashionsdk.compose.resources.drawable.AiutaComposeDrawableResource
import com.aiuta.fashionsdk.compose.resources.drawable.AiutaIcon
import com.aiuta.fashionsdk.configuration.features.onboarding.bestresult.icons.AiutaOnboardingBestResultsPageFeatureIcons

/**
 * Default implementation of [AiutaOnboardingBestResultsPageFeatureIcons].
 *
 * This class provides the default icon resources for the onboarding best results page,
 * including icons for good and bad results examples.
 *
 * @property onboardingBestResultsGood24 24x24 pixel icon showing a good example of try-on results
 * @property onboardingBestResultsBad24 24x24 pixel icon showing a bad example of try-on results
 */
public class DefaultAiutaOnboardingBestResultsPageFeatureIcons : AiutaOnboardingBestResultsPageFeatureIcons {
    override val onboardingBestResultsGood24: AiutaIcon = AiutaIcon(
        iconResource = AiutaComposeDrawableResource(Res.drawable.ic_onboarding_best_results_good_24),
    )
    override val onboardingBestResultsBad24: AiutaIcon = AiutaIcon(
        iconResource = AiutaComposeDrawableResource(Res.drawable.ic_onboarding_best_results_bad_24),
    )
}
