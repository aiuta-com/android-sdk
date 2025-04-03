package com.aiuta.fashionsdk.tryon.compose.configuration.features.tryon

import com.aiuta.fashionsdk.tryon.compose.configuration.features.AiutaFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.AiutaTryOnFeatures
import com.aiuta.fashionsdk.tryon.compose.configuration.features.tryon.dataprovider.AiutaTryOnFeatureDataProvider
import com.aiuta.fashionsdk.tryon.compose.configuration.features.tryon.disclaimer.AiutaTryOnFitDisclaimerFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.tryon.feedback.AiutaTryOnFeedbackFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.tryon.history.AiutaTryOnGenerationsHistoryFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.tryon.loading.AiutaTryOnLoadingPageFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.tryon.strings.AiutaTryOnFeatureStrings
import com.aiuta.fashionsdk.tryon.compose.configuration.features.tryon.styles.AiutaTryOnFeatureStyles
import com.aiuta.fashionsdk.tryon.compose.configuration.features.tryon.toggles.AiutaTryOnFeatureToggles
import com.aiuta.fashionsdk.tryon.compose.configuration.utils.checkNotNullWithDescription

public class AiutaTryOnFeature(
    // Features
    public val loadingPage: AiutaTryOnLoadingPageFeature,
    public val fitDisclaimer: AiutaTryOnFitDisclaimerFeature?,
    public val feedback: AiutaTryOnFeedbackFeature?,
    public val generationsHistory: AiutaTryOnGenerationsHistoryFeature?,
    // General
    public val toggles: AiutaTryOnFeatureToggles,
    public val strings: AiutaTryOnFeatureStrings,
    public val styles: AiutaTryOnFeatureStyles,
    public val dataProvider: AiutaTryOnFeatureDataProvider?,
) : AiutaFeature {

    public class Builder : AiutaFeature.Builder {
        public var loadingPage: AiutaTryOnLoadingPageFeature? = null
        public var fitDisclaimer: AiutaTryOnFitDisclaimerFeature? = null
        public var feedback: AiutaTryOnFeedbackFeature? = null
        public var generationsHistory: AiutaTryOnGenerationsHistoryFeature? = null
        public var toggles: AiutaTryOnFeatureToggles? = null
        public var strings: AiutaTryOnFeatureStrings? = null
        public var styles: AiutaTryOnFeatureStyles? = null
        public var dataProvider: AiutaTryOnFeatureDataProvider? = null

        public override fun build(): AiutaTryOnFeature {
            val parentClass = "AiutaTryOnFeature"

            return AiutaTryOnFeature(
                loadingPage = loadingPage.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "loadingPage",
                ),
                fitDisclaimer = fitDisclaimer,
                feedback = feedback,
                generationsHistory = generationsHistory,
                toggles = toggles.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "toggles",
                ),
                strings = strings.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "strings",
                ),
                styles = styles.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "styles",
                ),
                dataProvider = dataProvider,
            )
        }
    }
}

public inline fun AiutaTryOnFeatures.Builder.tryOn(
    block: AiutaTryOnFeature.Builder.() -> Unit,
): AiutaTryOnFeatures.Builder = apply {
    tryOn = AiutaTryOnFeature.Builder().apply(block).build()
}
