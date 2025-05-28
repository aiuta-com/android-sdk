package com.aiuta.fashionsdk.configuration.features.welcome

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.configuration.features.AiutaFeature
import com.aiuta.fashionsdk.configuration.features.AiutaFeatures
import com.aiuta.fashionsdk.configuration.features.welcome.icons.AiutaWelcomeScreenFeatureIcons
import com.aiuta.fashionsdk.configuration.features.welcome.images.AiutaWelcomeScreenFeatureImages
import com.aiuta.fashionsdk.configuration.features.welcome.strings.AiutaWelcomeScreenFeatureStrings
import com.aiuta.fashionsdk.configuration.features.welcome.typography.AiutaWelcomeScreenFeatureTypography
import com.aiuta.fashionsdk.configuration.internal.utils.checkNotNullWithDescription

/**
 * Configuration for the welcome screen feature in the fashion SDK.
 * 
 * This feature provides the initial screen shown to users when they first open the app,
 * including welcome images, icons, text, and typography settings.
 * 
 * Required components:
 * - [images]: Images displayed on the welcome screen
 * - [icons]: Icons used in the welcome screen UI
 * - [strings]: Text strings for the welcome screen
 * - [typography]: Typography settings for text elements
 */
@Immutable
public class AiutaWelcomeScreenFeature(
    public val images: AiutaWelcomeScreenFeatureImages,
    public val icons: AiutaWelcomeScreenFeatureIcons,
    public val strings: AiutaWelcomeScreenFeatureStrings,
    public val typography: AiutaWelcomeScreenFeatureTypography,
) : AiutaFeature {

    /**
     * Builder class for creating instances of [AiutaWelcomeScreenFeature].
     * 
     * This builder ensures that all required components are provided before
     * creating the feature instance.
     */
    public class Builder : AiutaFeature.Builder {
        public var images: AiutaWelcomeScreenFeatureImages? = null
        public var icons: AiutaWelcomeScreenFeatureIcons? = null
        public var strings: AiutaWelcomeScreenFeatureStrings? = null
        public var typography: AiutaWelcomeScreenFeatureTypography? = null

        public override fun build(): AiutaWelcomeScreenFeature {
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

/**
 * DSL function for configuring the welcome screen feature.
 * 
 * Example usage:
 * ```
 * features {
 *     welcomeScreen {
 *         // Configure welcome screen feature
 *         images = ...
 *         icons = ...
 *         strings = ...
 *         typography = ...
 *     }
 * }
 * ```
 */
public inline fun AiutaFeatures.Builder.welcomeScreen(
    block: AiutaWelcomeScreenFeature.Builder.() -> Unit,
): AiutaFeatures.Builder = apply {
    welcomeScreen = AiutaWelcomeScreenFeature.Builder().apply(block).build()
}
