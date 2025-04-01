package com.aiuta.fashionsdk.tryon.compose.configuration.features.welcome

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.annotations.AiutaDsl
import com.aiuta.fashionsdk.tryon.compose.configuration.features.AiutaTryOnFeatures
import com.aiuta.fashionsdk.tryon.compose.configuration.features.welcome.icons.AiutaWelcomeScreenFeatureIcons
import com.aiuta.fashionsdk.tryon.compose.configuration.features.welcome.images.AiutaWelcomeScreenFeatureImages
import com.aiuta.fashionsdk.tryon.compose.configuration.features.welcome.strings.AiutaWelcomeScreenFeatureStrings
import com.aiuta.fashionsdk.tryon.compose.configuration.features.welcome.typography.AiutaWelcomeScreenFeatureTypography
import com.aiuta.fashionsdk.tryon.compose.configuration.utils.checkNotNullWithDescription

@Immutable
public class AiutaWelcomeScreenFeature private constructor(
    public val images: AiutaWelcomeScreenFeatureImages,
    public val icons: AiutaWelcomeScreenFeatureIcons,
    public val strings: AiutaWelcomeScreenFeatureStrings,
    public val typography: AiutaWelcomeScreenFeatureTypography,
) {
    @AiutaDsl
    public class Builder {
        public var images: AiutaWelcomeScreenFeatureImages? = null
        public var icons: AiutaWelcomeScreenFeatureIcons? = null
        public var strings: AiutaWelcomeScreenFeatureStrings? = null
        public var typography: AiutaWelcomeScreenFeatureTypography? = null

        public fun build(): AiutaWelcomeScreenFeature {
            val parentClass = "AiutaWelcomeScreenFeature"

            return AiutaWelcomeScreenFeature(
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
                typography = typography.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "typography",
                ),
            )
        }
    }
}

public inline fun AiutaTryOnFeatures.Builder.welcomeScreen(
    block: AiutaWelcomeScreenFeature.Builder.() -> Unit,
) {
    welcomeScreen = AiutaWelcomeScreenFeature.Builder().apply(block).build()
}
