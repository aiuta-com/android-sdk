package com.aiuta.fashionsdk.tryon.compose.configuration.features.selector.history

import com.aiuta.fashionsdk.annotations.AiutaDsl
import com.aiuta.fashionsdk.tryon.compose.configuration.features.selector.AiutaImageSelectorFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.selector.history.buttons.AiutaImageSelectorUploadsHistoryButtons
import com.aiuta.fashionsdk.tryon.compose.configuration.features.selector.history.dataprovider.AiutaImageSelectorUploadsHistoryDataProvider
import com.aiuta.fashionsdk.tryon.compose.configuration.features.selector.history.strings.AiutaImageSelectorUploadsHistoryStrings
import com.aiuta.fashionsdk.tryon.compose.configuration.utils.checkNotNullWithDescription

public class AiutaImageSelectorUploadsHistory private constructor(
    public val strings: AiutaImageSelectorUploadsHistoryStrings,
    public val buttons: AiutaImageSelectorUploadsHistoryButtons,
    public val dataProvider: AiutaImageSelectorUploadsHistoryDataProvider?,
) {
    @AiutaDsl
    public class Builder {
        public var strings: AiutaImageSelectorUploadsHistoryStrings? = null
        public var buttons: AiutaImageSelectorUploadsHistoryButtons? = null
        public var dataProvider: AiutaImageSelectorUploadsHistoryDataProvider? = null

        public fun build(): AiutaImageSelectorUploadsHistory {
            val parentClass = "AiutaImageSelectorUploadsHistory"

            return AiutaImageSelectorUploadsHistory(
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
    block: AiutaImageSelectorUploadsHistory.Builder.() -> Unit,
) {
    uploadsHistory = AiutaImageSelectorUploadsHistory.Builder().apply(block).build()
}
