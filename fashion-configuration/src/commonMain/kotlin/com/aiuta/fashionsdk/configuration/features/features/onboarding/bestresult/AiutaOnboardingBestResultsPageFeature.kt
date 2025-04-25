package com.aiuta.fashionsdk.configuration.features.features.onboarding.bestresult

import com.aiuta.fashionsdk.configuration.features.features.AiutaTryOnConfigurationFeature
import com.aiuta.fashionsdk.configuration.features.features.onboarding.AiutaOnboardingFeature
import com.aiuta.fashionsdk.configuration.features.features.onboarding.bestresult.icons.AiutaOnboardingBestResultsPageFeatureIcons
import com.aiuta.fashionsdk.configuration.features.features.onboarding.bestresult.images.AiutaOnboardingBestResultsPageFeatureImages
import com.aiuta.fashionsdk.configuration.features.features.onboarding.bestresult.strings.AiutaOnboardingBestResultsPageFeatureStrings
import com.aiuta.fashionsdk.configuration.features.features.onboarding.bestresult.toggles.AiutaOnboardingBestResultsPageFeatureToggles
import com.aiuta.fashionsdk.configuration.internal.utils.checkNotNullWithDescription

public class AiutaOnboardingBestResultsPageFeature private constructor(
    public val images: AiutaOnboardingBestResultsPageFeatureImages,
    public val icons: AiutaOnboardingBestResultsPageFeatureIcons,
    public val strings: AiutaOnboardingBestResultsPageFeatureStrings,
    public val toggles: AiutaOnboardingBestResultsPageFeatureToggles,
) : AiutaTryOnConfigurationFeature {

    public class Builder : AiutaTryOnConfigurationFeature.Builder {
        public var images: AiutaOnboardingBestResultsPageFeatureImages? = null
        public var icons: AiutaOnboardingBestResultsPageFeatureIcons? = null
        public var strings: AiutaOnboardingBestResultsPageFeatureStrings? = null
        public var toggles: AiutaOnboardingBestResultsPageFeatureToggles? = null

        public override fun build(): AiutaOnboardingBestResultsPageFeature {
            val parentClass = "AiutaOnboardingBestResultsPage"

            return AiutaOnboardingBestResultsPageFeature(
                images = images.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "images",
                ),
                icons = icons.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "icons",
                ),
                strings = strings.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "strings",
                ),
                toggles = toggles.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "toggles",
                ),
            )
        }
    }
}

public inline fun AiutaOnboardingFeature.Builder.bestResultsPage(
    block: AiutaOnboardingBestResultsPageFeature.Builder.() -> Unit,
): AiutaOnboardingFeature.Builder = apply {
    bestResultsPage = AiutaOnboardingBestResultsPageFeature.Builder().apply(block).build()
}
