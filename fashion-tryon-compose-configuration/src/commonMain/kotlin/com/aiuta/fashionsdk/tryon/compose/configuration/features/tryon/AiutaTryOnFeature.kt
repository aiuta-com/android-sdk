package com.aiuta.fashionsdk.tryon.compose.configuration.features.tryon

import com.aiuta.fashionsdk.tryon.compose.configuration.features.AiutaTryOnConfigurationFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.AiutaTryOnConfigurationFeatures
import com.aiuta.fashionsdk.tryon.compose.configuration.features.tryon.dataprovider.AiutaTryOnFeatureDataProvider
import com.aiuta.fashionsdk.tryon.compose.configuration.features.tryon.disclaimer.AiutaTryOnFitDisclaimerFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.tryon.feedback.AiutaTryOnFeedbackFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.tryon.history.AiutaTryOnGenerationsHistoryFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.tryon.icons.AiutaTryOnFeatureIcons
import com.aiuta.fashionsdk.tryon.compose.configuration.features.tryon.loading.AiutaTryOnLoadingPageFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.tryon.repicking.AiutaTryOnRepickingFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.tryon.strings.AiutaTryOnFeatureStrings
import com.aiuta.fashionsdk.tryon.compose.configuration.features.tryon.styles.AiutaTryOnFeatureStyles
import com.aiuta.fashionsdk.tryon.compose.configuration.features.tryon.toggles.AiutaTryOnFeatureToggles
import com.aiuta.fashionsdk.tryon.compose.configuration.internal.utils.checkNotNullWithDescription

public class AiutaTryOnFeature private constructor(
    // Features
    public val loadingPage: AiutaTryOnLoadingPageFeature,
    public val fitDisclaimer: AiutaTryOnFitDisclaimerFeature?,
    public val feedback: AiutaTryOnFeedbackFeature?,
    public val generationsHistory: AiutaTryOnGenerationsHistoryFeature?,
    public val repicking: AiutaTryOnRepickingFeature?,
    // General
    public val icons: AiutaTryOnFeatureIcons,
    public val toggles: AiutaTryOnFeatureToggles,
    public val strings: AiutaTryOnFeatureStrings,
    public val styles: AiutaTryOnFeatureStyles,
    public val dataProvider: AiutaTryOnFeatureDataProvider?,
) : AiutaTryOnConfigurationFeature {

    public class Builder : AiutaTryOnConfigurationFeature.Builder {
        public var loadingPage: AiutaTryOnLoadingPageFeature? = null
        public var fitDisclaimer: AiutaTryOnFitDisclaimerFeature? = null
        public var feedback: AiutaTryOnFeedbackFeature? = null
        public var generationsHistory: AiutaTryOnGenerationsHistoryFeature? = null
        public var repicking: AiutaTryOnRepickingFeature? = null

        public var icons: AiutaTryOnFeatureIcons? = null
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
                repicking = repicking,
                icons = icons.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "icons",
                ),
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

public inline fun AiutaTryOnConfigurationFeatures.Builder.tryOn(
    block: AiutaTryOnFeature.Builder.() -> Unit,
): AiutaTryOnConfigurationFeatures.Builder = apply {
    tryOn = AiutaTryOnFeature.Builder().apply(block).build()
}
