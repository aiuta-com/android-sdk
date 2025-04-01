package com.aiuta.fashionsdk.tryon.compose.defaults.features.onboarding

import com.aiuta.fashionsdk.tryon.compose.configuration.features.AiutaTryOnFeatures
import com.aiuta.fashionsdk.tryon.compose.configuration.features.onboarding.bestresult.strings.AiutaOnboardingBestResultsPageStrings
import com.aiuta.fashionsdk.tryon.compose.configuration.features.onboarding.bestresult.toggles.AiutaOnboardingBestResultsPageToggles
import com.aiuta.fashionsdk.tryon.compose.configuration.features.onboarding.onboarding
import com.aiuta.fashionsdk.tryon.compose.configuration.features.onboarding.shapes.AiutaOnboardingFeatureShapes
import com.aiuta.fashionsdk.tryon.compose.configuration.features.onboarding.strings.AiutaOnboardingFeatureStrings
import com.aiuta.fashionsdk.tryon.compose.configuration.features.onboarding.tryon.strings.AiutaOnboardingTryOnPageStrings
import com.aiuta.fashionsdk.tryon.compose.defaults.icons.features.onboarding.bestresult.DefaultAiutaOnboardingBestResultsPageIcons
import com.aiuta.fashionsdk.tryon.compose.defaults.images.features.onboarding.bestresult.DefaultsAiutaOnboardingBestResultsPageImages
import com.aiuta.fashionsdk.tryon.compose.defaults.images.features.onboarding.tryon.DefaultAiutaOnboardingTryOnPageImages

public fun AiutaTryOnFeatures.Builder.defaultOnboarding() {
    onboarding {
        bestResultsPage {
            images = DefaultsAiutaOnboardingBestResultsPageImages()
            icons = DefaultAiutaOnboardingBestResultsPageIcons()
            strings = AiutaOnboardingBestResultsPageStrings.Default()
            toggles = AiutaOnboardingBestResultsPageToggles.Default()
        }
        tryOnPage {
            images = DefaultAiutaOnboardingTryOnPageImages()
            strings = AiutaOnboardingTryOnPageStrings.Default()
        }
        strings = AiutaOnboardingFeatureStrings.Default()
        shapes = AiutaOnboardingFeatureShapes.Default()
    }
}
