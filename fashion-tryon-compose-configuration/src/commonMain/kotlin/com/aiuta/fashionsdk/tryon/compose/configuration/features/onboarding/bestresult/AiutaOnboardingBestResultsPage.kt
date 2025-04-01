package com.aiuta.fashionsdk.tryon.compose.configuration.features.onboarding.bestresult

import com.aiuta.fashionsdk.tryon.compose.configuration.annotations.AiutaTryOnConfigurationDsl
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
) {
    @AiutaTryOnConfigurationDsl
    public class Builder {
        public var images: AiutaOnboardingBestResultsPageImages? = null
        public var icons: AiutaOnboardingBestResultsPageIcons? = null
        public var strings: AiutaOnboardingBestResultsPageStrings? = null
        public var toggles: AiutaOnboardingBestResultsPageToggles? = null

        public fun build(): AiutaOnboardingBestResultsPage {
            val parentClass = "AiutaOnboardingBestResultsPage"

            return AiutaOnboardingBestResultsPage(
                images =
                    this.images.checkNotNullWithDescription(
                        parentClass = parentClass,
                        property = "images",
                    ),
                icons =
                    this.icons.checkNotNullWithDescription(
                        parentClass = parentClass,
                        property = "icons",
                    ),
                strings =
                    this.strings.checkNotNullWithDescription(
                        parentClass = parentClass,
                        property = "strings",
                    ),
                toggles =
                    this.toggles.checkNotNullWithDescription(
                        parentClass = parentClass,
                        property = "toggles",
                    ),
            )
        }
    }
}
