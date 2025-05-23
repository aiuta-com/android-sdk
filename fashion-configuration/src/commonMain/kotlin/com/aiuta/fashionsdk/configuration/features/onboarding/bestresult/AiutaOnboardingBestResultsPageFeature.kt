package com.aiuta.fashionsdk.configuration.features.onboarding.bestresult

import com.aiuta.fashionsdk.configuration.features.AiutaFeature
import com.aiuta.fashionsdk.configuration.features.onboarding.AiutaOnboardingFeature
import com.aiuta.fashionsdk.configuration.features.onboarding.bestresult.icons.AiutaOnboardingBestResultsPageFeatureIcons
import com.aiuta.fashionsdk.configuration.features.onboarding.bestresult.images.AiutaOnboardingBestResultsPageFeatureImages
import com.aiuta.fashionsdk.configuration.features.onboarding.bestresult.strings.AiutaOnboardingBestResultsPageFeatureStrings
import com.aiuta.fashionsdk.configuration.features.onboarding.bestresult.styles.AiutaOnboardingBestResultsPageFeatureStyles
import com.aiuta.fashionsdk.configuration.internal.utils.checkNotNullWithDescription

public class AiutaOnboardingBestResultsPageFeature(
    public val images: AiutaOnboardingBestResultsPageFeatureImages,
    public val icons: AiutaOnboardingBestResultsPageFeatureIcons,
    public val strings: AiutaOnboardingBestResultsPageFeatureStrings,
    public val styles: AiutaOnboardingBestResultsPageFeatureStyles,
) : AiutaFeature {

    public class Builder : AiutaFeature.Builder {
        public var images: AiutaOnboardingBestResultsPageFeatureImages? = null
        public var icons: AiutaOnboardingBestResultsPageFeatureIcons? = null
        public var strings: AiutaOnboardingBestResultsPageFeatureStrings? = null
        public var styles: AiutaOnboardingBestResultsPageFeatureStyles? = null

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
                styles = styles.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "styles",
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
