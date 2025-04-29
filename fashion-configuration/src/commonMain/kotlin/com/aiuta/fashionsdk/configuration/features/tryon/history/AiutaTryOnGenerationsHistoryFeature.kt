package com.aiuta.fashionsdk.configuration.features.tryon.history

import com.aiuta.fashionsdk.configuration.features.AiutaFeature
import com.aiuta.fashionsdk.configuration.features.tryon.AiutaTryOnFeature
import com.aiuta.fashionsdk.configuration.features.tryon.history.dataprovider.AiutaTryOnGenerationsHistoryFeatureDataProvider
import com.aiuta.fashionsdk.configuration.features.tryon.history.icons.AiutaTryOnGenerationsHistoryFeatureIcons
import com.aiuta.fashionsdk.configuration.features.tryon.history.strings.AiutaTryOnGenerationsHistoryFeatureStrings
import com.aiuta.fashionsdk.configuration.internal.utils.checkNotNullWithDescription

public class AiutaTryOnGenerationsHistoryFeature(
    public val icons: AiutaTryOnGenerationsHistoryFeatureIcons,
    public val strings: AiutaTryOnGenerationsHistoryFeatureStrings,
    public val dataProvider: AiutaTryOnGenerationsHistoryFeatureDataProvider?,
) : AiutaFeature {

    public class Builder : AiutaFeature.Builder {
        public var icons: AiutaTryOnGenerationsHistoryFeatureIcons? = null
        public var strings: AiutaTryOnGenerationsHistoryFeatureStrings? = null
        public var dataProvider: AiutaTryOnGenerationsHistoryFeatureDataProvider? = null

        public override fun build(): AiutaTryOnGenerationsHistoryFeature {
            val parentClass = "AiutaTryOnGenerationsHistoryFeature"

            return AiutaTryOnGenerationsHistoryFeature(
                icons = icons.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "icons",
                ),
                strings = strings.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "strings",
                ),
                dataProvider = dataProvider,
            )
        }
    }
}

public inline fun AiutaTryOnFeature.Builder.generationsHistory(
    block: AiutaTryOnGenerationsHistoryFeature.Builder.() -> Unit,
): AiutaTryOnFeature.Builder = apply {
    generationsHistory = AiutaTryOnGenerationsHistoryFeature.Builder().apply(block).build()
}
