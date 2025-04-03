package com.aiuta.fashionsdk.tryon.compose.configuration.features.tryon.history

import com.aiuta.fashionsdk.tryon.compose.configuration.features.AiutaTryOnConfigurationFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.tryon.AiutaTryOnFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.tryon.history.dataprovider.AiutaTryOnGenerationsHistoryFeatureDataProvider
import com.aiuta.fashionsdk.tryon.compose.configuration.features.tryon.history.icons.AiutaTryOnGenerationsHistoryFeatureIcons
import com.aiuta.fashionsdk.tryon.compose.configuration.utils.checkNotNullWithDescription

public class AiutaTryOnGenerationsHistoryFeature(
    public val icons: AiutaTryOnGenerationsHistoryFeatureIcons,
    public val dataProvider: AiutaTryOnGenerationsHistoryFeatureDataProvider?,
) : AiutaTryOnConfigurationFeature {

    public class Builder : AiutaTryOnConfigurationFeature.Builder {
        public var icons: AiutaTryOnGenerationsHistoryFeatureIcons? = null
        public var dataProvider: AiutaTryOnGenerationsHistoryFeatureDataProvider? = null

        public override fun build(): AiutaTryOnGenerationsHistoryFeature {
            val parentClass = "AiutaTryOnGenerationsHistoryFeature"

            return AiutaTryOnGenerationsHistoryFeature(
                icons = icons.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "icons",
                ),
                dataProvider = dataProvider,
            )
        }
    }
}

public inline fun AiutaTryOnFeature.Builder.fitDisclaimer(
    block: AiutaTryOnGenerationsHistoryFeature.Builder.() -> Unit,
): AiutaTryOnFeature.Builder = apply {
    generationsHistory = AiutaTryOnGenerationsHistoryFeature.Builder().apply(block).build()
}
