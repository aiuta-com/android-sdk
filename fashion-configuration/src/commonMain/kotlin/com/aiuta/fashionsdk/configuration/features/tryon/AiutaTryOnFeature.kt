package com.aiuta.fashionsdk.configuration.features.tryon

import com.aiuta.fashionsdk.configuration.features.AiutaFeature
import com.aiuta.fashionsdk.configuration.features.AiutaFeatures
import com.aiuta.fashionsdk.configuration.features.tryon.cart.AiutaTryOnCartFeature
import com.aiuta.fashionsdk.configuration.features.tryon.disclaimer.AiutaTryOnFitDisclaimerFeature
import com.aiuta.fashionsdk.configuration.features.tryon.feedback.AiutaTryOnFeedbackFeature
import com.aiuta.fashionsdk.configuration.features.tryon.history.AiutaTryOnGenerationsHistoryFeature
import com.aiuta.fashionsdk.configuration.features.tryon.icons.AiutaTryOnFeatureIcons
import com.aiuta.fashionsdk.configuration.features.tryon.loading.AiutaTryOnLoadingPageFeature
import com.aiuta.fashionsdk.configuration.features.tryon.other.AiutaTryOnWithOtherPhotoFeature
import com.aiuta.fashionsdk.configuration.features.tryon.strings.AiutaTryOnFeatureStrings
import com.aiuta.fashionsdk.configuration.features.tryon.styles.AiutaTryOnFeatureStyles
import com.aiuta.fashionsdk.configuration.features.tryon.toggles.AiutaTryOnFeatureToggles
import com.aiuta.fashionsdk.configuration.features.tryon.validation.AiutaTryOnInputImageValidationFeature
import com.aiuta.fashionsdk.configuration.internal.utils.checkNotNullWithDescription

/**
 * Try-on feature configuration for the Aiuta SDK.
 *
 * This is the core feature of the SDK that manages the virtual try-on experience.
 * It provides configuration for all aspects of the try-on process including
 * loading states, image validation, shopping cart integration, user feedback,
 * and various optional enhancements.
 *
 * Required components:
 * - [loadingPage]: Loading page during try-on generation
 * - [inputImageValidation]: Input image validation and processing
 * - [cart]: Shopping cart integration
 * - [icons]: Icon resources for try-on UI
 * - [toggles]: Feature toggles and behavior settings
 * - [strings]: Text content and localization
 * - [styles]: Visual styling configuration
 *
 * Optional components:
 * - [fitDisclaimer]: Fit disclaimer information
 * - [feedback]: User feedback collection
 * - [generationsHistory]: History of generated try-on images
 * - [otherPhoto]: Try-on with different photos functionality
 *
 *
 * @property loadingPage Required loading page feature configuration
 * @property inputImageValidation Required input image validation feature configuration
 * @property cart Required shopping cart integration feature configuration
 * @property fitDisclaimer Optional fit disclaimer feature configuration
 * @property feedback Optional user feedback feature configuration
 * @property generationsHistory Optional generations history feature configuration
 * @property otherPhoto Optional try-on with other photos feature configuration
 * @property icons Required icon resources configuration
 * @property toggles Required toggles and behavior settings
 * @property strings Required text content and localization configuration
 * @property styles Required visual styling configuration
 * @see AiutaFeature
 * @see AiutaTryOnLoadingPageFeature
 * @see AiutaTryOnCartFeature
 */
public class AiutaTryOnFeature(
    // Features
    public val loadingPage: AiutaTryOnLoadingPageFeature,
    public val inputImageValidation: AiutaTryOnInputImageValidationFeature,
    public val cart: AiutaTryOnCartFeature,
    public val fitDisclaimer: AiutaTryOnFitDisclaimerFeature?,
    public val feedback: AiutaTryOnFeedbackFeature?,
    public val generationsHistory: AiutaTryOnGenerationsHistoryFeature?,
    public val otherPhoto: AiutaTryOnWithOtherPhotoFeature?,
    // General
    public val icons: AiutaTryOnFeatureIcons,
    public val toggles: AiutaTryOnFeatureToggles,
    public val strings: AiutaTryOnFeatureStrings,
    public val styles: AiutaTryOnFeatureStyles,
) : AiutaFeature {

    /**
     * Builder class for creating [AiutaTryOnFeature] instances.
     *
     * This builder ensures all required sub-features are configured before
     * creating the final try-on feature configuration.
     */
    public class Builder : AiutaFeature.Builder {
        /**
         * Required loading page feature configuration.
         */
        public var loadingPage: AiutaTryOnLoadingPageFeature? = null

        /**
         * Required input image validation feature configuration.
         */
        public var inputImageValidation: AiutaTryOnInputImageValidationFeature? = null

        /**
         * Required shopping cart integration feature configuration.
         */
        public var cart: AiutaTryOnCartFeature? = null

        /**
         * Optional fit disclaimer feature configuration.
         */
        public var fitDisclaimer: AiutaTryOnFitDisclaimerFeature? = null

        /**
         * Optional user feedback feature configuration.
         */
        public var feedback: AiutaTryOnFeedbackFeature? = null

        /**
         * Optional generations history feature configuration.
         */
        public var generationsHistory: AiutaTryOnGenerationsHistoryFeature? = null

        /**
         * Optional try-on with other photos feature configuration.
         */
        public var otherPhoto: AiutaTryOnWithOtherPhotoFeature? = null

        /**
         * Required icon resources configuration.
         */
        public var icons: AiutaTryOnFeatureIcons? = null

        /**
         * Required feature toggles and behavior settings.
         */
        public var toggles: AiutaTryOnFeatureToggles? = null

        /**
         * Required text content and localization configuration.
         */
        public var strings: AiutaTryOnFeatureStrings? = null

        /**
         * Required visual styling configuration.
         */
        public var styles: AiutaTryOnFeatureStyles? = null

        /**
         * Creates an [AiutaTryOnFeature] instance with the configured properties.
         *
         * @return Configured [AiutaTryOnFeature] instance
         * @throws IllegalArgumentException if required properties are not set
         */
        public override fun build(): AiutaTryOnFeature {
            val parentClass = "AiutaTryOnFeature"

            return AiutaTryOnFeature(
                loadingPage = loadingPage.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "loadingPage",
                ),
                inputImageValidation = inputImageValidation.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "inputImageValidation",
                ),
                cart = cart.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "cart",
                ),
                fitDisclaimer = fitDisclaimer,
                feedback = feedback,
                generationsHistory = generationsHistory,
                otherPhoto = otherPhoto,
                icons = icons.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "icons",
                ),
                toggles = toggles.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "styles",
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

/**
 * Extension function for configuring try-on within an [AiutaFeatures.Builder].
 *
 * This extension provides a convenient DSL for configuring the try-on feature
 * as part of the main features setup.
 *
 * ```kotlin
 * val features = aiutaFeatures {
 *     tryOn {
 *         // Required
 *         loadingPage {
 *             // Configure loading page feature
 *         }
 *         inputImageValidation {
 *             // Configure input validation feature
 *         }
 *         cart {
 *             // Configure cart feature
 *         }
 *         icons = ...
 *         toggles = ...
 *         strings = ...
 *         styles = ...
 *
 *         // Optional features
 *         otherPhoto {
 *             // Configure other photo generation feature
 *         }
 *         feedback {
 *             // Configure feedback feature
 *         }
 *         generationsHistory {
 *             // Configure generation history feature
 *         }
 *     }
 * }
 * ```
 * ```
 *
 * @param block Configuration block for setting up the try-on feature
 * @return The features builder for method chaining
 * @see AiutaFeatures.Builder
 * @see AiutaTryOnFeature.Builder
 */
public inline fun AiutaFeatures.Builder.tryOn(
    block: AiutaTryOnFeature.Builder.() -> Unit,
): AiutaFeatures.Builder = apply {
    tryOn = AiutaTryOnFeature.Builder().apply(block).build()
}
