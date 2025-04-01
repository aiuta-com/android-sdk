package com.aiuta.fashionsdk.tryon.compose.configuration.features

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.annotations.AiutaDsl
import com.aiuta.fashionsdk.tryon.compose.configuration.features.consent.AiutaConsentFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.onboarding.AiutaOnboardingFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.selector.AiutaImageSelectorFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.welcome.AiutaWelcomeScreenFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.utils.checkNotNullWithDescription

@Immutable
public class AiutaTryOnFeatures private constructor(
    public val welcomeScreen: AiutaWelcomeScreenFeature?,
    public val onboarding: AiutaOnboardingFeature?,
    public val consent: AiutaConsentFeature?,
    public var imageSelector: AiutaImageSelectorFeature,
) {
    @AiutaDsl
    public class Builder {
        public var welcomeScreen: AiutaWelcomeScreenFeature? = null
        public var onboarding: AiutaOnboardingFeature? = null
        public var consent: AiutaConsentFeature? = null
        public var imageSelector: AiutaImageSelectorFeature? = null

        public fun build(): AiutaTryOnFeatures {
            val parentClass = "AiutaTryOnFeatures"
            return AiutaTryOnFeatures(
                welcomeScreen = welcomeScreen,
                onboarding = onboarding,
                consent = consent,
                imageSelector = imageSelector.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "imageSelector",
                ),
            )
        }
    }
}

public inline fun aiutaTryOnFeatures(
    block: AiutaTryOnFeatures.Builder.() -> Unit,
): AiutaTryOnFeatures = AiutaTryOnFeatures.Builder().apply(block).build()
