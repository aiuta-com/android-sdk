package com.aiuta.fashionsdk.tryon.compose.configuration.features.selector.history

import com.aiuta.fashionsdk.tryon.compose.configuration.features.AiutaTryOnConfigurationFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.selector.AiutaImageSelectorFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.selector.history.dataprovider.AiutaImageSelectorUploadsHistoryFeatureDataProvider
import com.aiuta.fashionsdk.tryon.compose.configuration.features.selector.history.strings.AiutaImageSelectorUploadsHistoryFeatureStrings
import com.aiuta.fashionsdk.tryon.compose.configuration.features.selector.history.styles.AiutaImageSelectorUploadsHistoryFeatureStyles
import com.aiuta.fashionsdk.tryon.compose.configuration.internal.utils.checkNotNullWithDescription

public class AiutaImageSelectorUploadsHistoryFeature private constructor(
    public val strings: AiutaImageSelectorUploadsHistoryFeatureStrings,
    public val styles: AiutaImageSelectorUploadsHistoryFeatureStyles,
    public val dataProvider: AiutaImageSelectorUploadsHistoryFeatureDataProvider?,
) : AiutaTryOnConfigurationFeature {
    public class Builder : AiutaTryOnConfigurationFeature.Builder {
        public var strings: AiutaImageSelectorUploadsHistoryFeatureStrings? = null
        public var styles: AiutaImageSelectorUploadsHistoryFeatureStyles? = null
        public var dataProvider: AiutaImageSelectorUploadsHistoryFeatureDataProvider? = null

        public override fun build(): AiutaImageSelectorUploadsHistoryFeature {
            val parentClass = "AiutaImageSelectorUploadsHistoryFeature"

            return AiutaImageSelectorUploadsHistoryFeature(
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

public inline fun AiutaImageSelectorFeature.Builder.uploadsHistory(
    block: AiutaImageSelectorUploadsHistoryFeature.Builder.() -> Unit,
): AiutaImageSelectorFeature.Builder = apply {
    uploadsHistory = AiutaImageSelectorUploadsHistoryFeature.Builder().apply(block).build()
}
