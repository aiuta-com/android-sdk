package com.aiuta.fashionsdk.tryon.compose.defaults.features.onboarding

import com.aiuta.fashionsdk.tryon.compose.configuration.features.AiutaTryOnConfigurationFeatures
import com.aiuta.fashionsdk.tryon.compose.configuration.features.onboarding.bestresult.bestResultsPage
import com.aiuta.fashionsdk.tryon.compose.configuration.features.onboarding.bestresult.strings.AiutaOnboardingBestResultsPageFeatureStrings
import com.aiuta.fashionsdk.tryon.compose.configuration.features.onboarding.bestresult.toggles.AiutaOnboardingBestResultsPageFeatureToggles
import com.aiuta.fashionsdk.tryon.compose.configuration.features.onboarding.onboarding
import com.aiuta.fashionsdk.tryon.compose.configuration.features.onboarding.shapes.AiutaOnboardingFeatureShapes
import com.aiuta.fashionsdk.tryon.compose.configuration.features.onboarding.strings.AiutaOnboardingFeatureStrings
import com.aiuta.fashionsdk.tryon.compose.configuration.features.onboarding.toggles.AiutaOnboardingFeatureToggles
import com.aiuta.fashionsdk.tryon.compose.configuration.features.onboarding.tryon.strings.AiutaOnboardingTryOnPageFeatureStrings
import com.aiuta.fashionsdk.tryon.compose.configuration.features.onboarding.tryon.tryOnPage
import com.aiuta.fashionsdk.tryon.compose.defaults.icons.features.onboarding.bestresult.DefaultAiutaOnboardingBestResultsPageFeatureIcons
import com.aiuta.fashionsdk.tryon.compose.defaults.images.features.onboarding.bestresult.DefaultsAiutaOnboardingBestResultsPageFeatureImages
import com.aiuta.fashionsdk.tryon.compose.defaults.images.features.onboarding.tryon.DefaultAiutaOnboardingTryOnPageFeatureImages

public fun AiutaTryOnConfigurationFeatures.Builder.defaultOnboarding() {
    onboarding {
        bestResultsPage {
            images = DefaultsAiutaOnboardingBestResultsPageFeatureImages()
            icons = DefaultAiutaOnboardingBestResultsPageFeatureIcons()
            strings = AiutaOnboardingBestResultsPageFeatureStrings.Default()
            toggles = AiutaOnboardingBestResultsPageFeatureToggles.Default()
        }
        tryOnPage {
            images = DefaultAiutaOnboardingTryOnPageFeatureImages()
            strings = AiutaOnboardingTryOnPageFeatureStrings.Default()
        }
        strings = AiutaOnboardingFeatureStrings.Default()
        shapes = AiutaOnboardingFeatureShapes.Default()
        toggles = AiutaOnboardingFeatureToggles.Default()
    }
}
