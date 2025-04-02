package com.aiuta.fashionsdk.tryon.compose.configuration.features.onboarding.bestresult

import com.aiuta.fashionsdk.tryon.compose.configuration.features.AiutaFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.onboarding.AiutaOnboardingFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.onboarding.bestresult.icons.AiutaOnboardingBestResultsPageIcons
import com.aiuta.fashionsdk.tryon.compose.configuration.features.onboarding.bestresult.images.AiutaOnboardingBestResultsPageImages
import com.aiuta.fashionsdk.tryon.compose.configuration.features.onboarding.bestresult.strings.AiutaOnboardingBestResultsPageStrings
import com.aiuta.fashionsdk.tryon.compose.configuration.features.onboarding.bestresult.toggles.AiutaOnboardingBestResultsPageToggles
import com.aiuta.fashionsdk.tryon.compose.configuration.utils.checkNotNullWithDescription

public class AiutaOnboardingBestResultsPage(
    public val images: AiutaOnboardingBestResultsPageImages,
    public val icons: AiutaOnboardingBestResultsPageIcons,
    public val strings: AiutaOnboardingBestResultsPageStrings,
    public val toggles: AiutaOnboardingBestResultsPageToggles,
) : AiutaFeature {

    public class Builder : AiutaFeature.Builder {
        public var images: AiutaOnboardingBestResultsPageImages? = null
        public var icons: AiutaOnboardingBestResultsPageIcons? = null
        public var strings: AiutaOnboardingBestResultsPageStrings? = null
        public var toggles: AiutaOnboardingBestResultsPageToggles? = null

        public override fun build(): AiutaOnboardingBestResultsPage {
            val parentClass = "AiutaOnboardingBestResultsPage"

            return AiutaOnboardingBestResultsPage(
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
    block: AiutaOnboardingBestResultsPage.Builder.() -> Unit,
) {
    bestResultsPage = AiutaOnboardingBestResultsPage.Builder().apply(block).build()
}
