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

    public class Builder : AiutaFeature.Builder {
        public var loadingPage: AiutaTryOnLoadingPageFeature? = null
        public var inputImageValidation: AiutaTryOnInputImageValidationFeature? = null
        public var cart: AiutaTryOnCartFeature? = null
        public var fitDisclaimer: AiutaTryOnFitDisclaimerFeature? = null
        public var feedback: AiutaTryOnFeedbackFeature? = null
        public var generationsHistory: AiutaTryOnGenerationsHistoryFeature? = null
        public var otherPhoto: AiutaTryOnWithOtherPhotoFeature? = null

        public var icons: AiutaTryOnFeatureIcons? = null
        public var toggles: AiutaTryOnFeatureToggles? = null
        public var strings: AiutaTryOnFeatureStrings? = null
        public var styles: AiutaTryOnFeatureStyles? = null

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

public inline fun AiutaFeatures.Builder.tryOn(
    block: AiutaTryOnFeature.Builder.() -> Unit,
): AiutaFeatures.Builder = apply {
    tryOn = AiutaTryOnFeature.Builder().apply(block).build()
}
