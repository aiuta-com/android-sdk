package com.aiuta.fashionsdk.configuration.defaults.images.features.onboarding.tryon

import com.aiuta.fashion_configuration_defaults_images.generated.resources.Res
import com.aiuta.fashion_configuration_defaults_images.generated.resources.onboarding_item_1
import com.aiuta.fashion_configuration_defaults_images.generated.resources.onboarding_item_2
import com.aiuta.fashion_configuration_defaults_images.generated.resources.onboarding_item_3
import com.aiuta.fashion_configuration_defaults_images.generated.resources.onboarding_main_1
import com.aiuta.fashion_configuration_defaults_images.generated.resources.onboarding_main_2
import com.aiuta.fashion_configuration_defaults_images.generated.resources.onboarding_main_3
import com.aiuta.fashionsdk.compose.resources.drawable.AiutaComposeDrawableResource
import com.aiuta.fashionsdk.configuration.features.onboarding.howworks.images.AiutaOnboardingHowItWorksPageFeatureImages

/**
 * Default implementation of [AiutaOnboardingHowItWorksPageFeatureImages].
 *
 * This class provides the default image resources for the onboarding "How It Works" page,
 * including a sequence of images that demonstrate the try-on process.
 *
 * @property onboardingHowItWorksItems List of [OnboardingHowItWorksItem] objects, each containing:
 *   - itemPhoto: Main photo demonstrating a step in the try-on process
 *   - itemPreview: Preview image showing the corresponding item being tried on
 */
public class DefaultAiutaOnboardingHowItWorksPageFeatureImages : AiutaOnboardingHowItWorksPageFeatureImages {
    override val onboardingHowItWorksItems: List<AiutaOnboardingHowItWorksPageFeatureImages.OnboardingHowItWorksItem> =
        listOf(
            AiutaOnboardingHowItWorksPageFeatureImages.OnboardingHowItWorksItem(
                itemPhoto = AiutaComposeDrawableResource(Res.drawable.onboarding_main_1),
                itemPreview = AiutaComposeDrawableResource(Res.drawable.onboarding_item_1),
            ),
            AiutaOnboardingHowItWorksPageFeatureImages.OnboardingHowItWorksItem(
                itemPhoto = AiutaComposeDrawableResource(Res.drawable.onboarding_main_2),
                itemPreview = AiutaComposeDrawableResource(Res.drawable.onboarding_item_2),
            ),
            AiutaOnboardingHowItWorksPageFeatureImages.OnboardingHowItWorksItem(
                itemPhoto = AiutaComposeDrawableResource(Res.drawable.onboarding_main_3),
                itemPreview = AiutaComposeDrawableResource(Res.drawable.onboarding_item_3),
            ),
        )
}
