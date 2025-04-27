package com.aiuta.fashionsdk.configuration.defaults.features.onboarding

import com.aiuta.fashionsdk.configuration.defaults.icons.features.onboarding.bestresult.DefaultAiutaOnboardingBestResultsPageFeatureIcons
import com.aiuta.fashionsdk.configuration.defaults.images.features.onboarding.bestresult.DefaultsAiutaOnboardingBestResultsPageFeatureImages
import com.aiuta.fashionsdk.configuration.defaults.images.features.onboarding.tryon.DefaultAiutaOnboardingTryOnPageFeatureImages
import com.aiuta.fashionsdk.configuration.features.AiutaFeatures
import com.aiuta.fashionsdk.configuration.features.onboarding.bestresult.bestResultsPage
import com.aiuta.fashionsdk.configuration.features.onboarding.bestresult.strings.AiutaOnboardingBestResultsPageFeatureStrings
import com.aiuta.fashionsdk.configuration.features.onboarding.bestresult.toggles.AiutaOnboardingBestResultsPageFeatureToggles
import com.aiuta.fashionsdk.configuration.features.onboarding.onboarding
import com.aiuta.fashionsdk.configuration.features.onboarding.shapes.AiutaOnboardingFeatureShapes
import com.aiuta.fashionsdk.configuration.features.onboarding.strings.AiutaOnboardingFeatureStrings
import com.aiuta.fashionsdk.configuration.features.onboarding.tryon.strings.AiutaOnboardingTryOnPageFeatureStrings
import com.aiuta.fashionsdk.configuration.features.onboarding.tryon.tryOnPage

public fun AiutaFeatures.Builder.defaultOnboarding(): AiutaFeatures.Builder = onboarding {
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
}
