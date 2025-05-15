package com.aiuta.fashionsdk.configuration.defaults.features.onboarding

import com.aiuta.fashionsdk.configuration.defaults.icons.features.onboarding.bestresult.DefaultAiutaOnboardingBestResultsPageFeatureIcons
import com.aiuta.fashionsdk.configuration.defaults.images.features.onboarding.bestresult.DefaultsAiutaOnboardingBestResultsPageFeatureImages
import com.aiuta.fashionsdk.configuration.defaults.images.features.onboarding.tryon.DefaultAiutaOnboardingHowItWorksPageFeatureImages
import com.aiuta.fashionsdk.configuration.features.AiutaFeatures
import com.aiuta.fashionsdk.configuration.features.onboarding.bestresult.bestResultsPage
import com.aiuta.fashionsdk.configuration.features.onboarding.bestresult.strings.AiutaOnboardingBestResultsPageFeatureStrings
import com.aiuta.fashionsdk.configuration.features.onboarding.bestresult.styles.AiutaOnboardingBestResultsPageFeatureStyles
import com.aiuta.fashionsdk.configuration.features.onboarding.dataprovider.AiutaOnboardingFeatureDataProviderBuiltIn
import com.aiuta.fashionsdk.configuration.features.onboarding.howworks.howItWorksPage
import com.aiuta.fashionsdk.configuration.features.onboarding.howworks.strings.AiutaOnboardingHowItWorksPageFeatureStrings
import com.aiuta.fashionsdk.configuration.features.onboarding.onboarding
import com.aiuta.fashionsdk.configuration.features.onboarding.shapes.AiutaOnboardingFeatureShapes
import com.aiuta.fashionsdk.configuration.features.onboarding.strings.AiutaOnboardingFeatureStrings

public fun AiutaFeatures.Builder.defaultOnboarding(): AiutaFeatures.Builder = onboarding {
    bestResultsPage {
        images = DefaultsAiutaOnboardingBestResultsPageFeatureImages()
        icons = DefaultAiutaOnboardingBestResultsPageFeatureIcons()
        strings = AiutaOnboardingBestResultsPageFeatureStrings.Default()
        styles = AiutaOnboardingBestResultsPageFeatureStyles.Default()
    }
    howItWorksPage {
        images = DefaultAiutaOnboardingHowItWorksPageFeatureImages()
        strings = AiutaOnboardingHowItWorksPageFeatureStrings.Default()
    }
    strings = AiutaOnboardingFeatureStrings.Default()
    shapes = AiutaOnboardingFeatureShapes.Default()
    dataProvider = AiutaOnboardingFeatureDataProviderBuiltIn
}
