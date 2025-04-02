package com.aiuta.fashionsdk.tryon.compose.configuration.features.selector.history

import com.aiuta.fashionsdk.tryon.compose.configuration.features.AiutaFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.selector.AiutaImageSelectorFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.selector.history.buttons.AiutaImageSelectorUploadsHistoryFeatureButtons
import com.aiuta.fashionsdk.tryon.compose.configuration.features.selector.history.dataprovider.AiutaImageSelectorUploadsHistoryFeatureDataProvider
import com.aiuta.fashionsdk.tryon.compose.configuration.features.selector.history.strings.AiutaImageSelectorUploadsHistoryFeatureStrings
import com.aiuta.fashionsdk.tryon.compose.configuration.utils.checkNotNullWithDescription

public class AiutaImageSelectorUploadsHistoryFeature private constructor(
    public val strings: AiutaImageSelectorUploadsHistoryFeatureStrings,
    public val buttons: AiutaImageSelectorUploadsHistoryFeatureButtons,
    public val dataProvider: AiutaImageSelectorUploadsHistoryFeatureDataProvider?,
) : AiutaFeature {
    public class Builder : AiutaFeature.Builder {
        public var strings: AiutaImageSelectorUploadsHistoryFeatureStrings? = null
        public var buttons: AiutaImageSelectorUploadsHistoryFeatureButtons? = null
        public var dataProvider: AiutaImageSelectorUploadsHistoryFeatureDataProvider? = null

        public override fun build(): AiutaImageSelectorUploadsHistoryFeature {
            val parentClass = "AiutaImageSelectorUploadsHistoryFeature"

            return AiutaImageSelectorUploadsHistoryFeature(
                strings = strings.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "strings",
                ),
                buttons = buttons.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "buttons",
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
