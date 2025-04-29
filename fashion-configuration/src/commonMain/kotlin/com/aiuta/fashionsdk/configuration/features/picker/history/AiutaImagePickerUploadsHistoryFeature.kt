package com.aiuta.fashionsdk.configuration.features.picker.history

import com.aiuta.fashionsdk.configuration.features.AiutaFeature
import com.aiuta.fashionsdk.configuration.features.picker.AiutaImagePickerFeature
import com.aiuta.fashionsdk.configuration.features.picker.history.dataprovider.AiutaImagePickerUploadsHistoryFeatureDataProvider
import com.aiuta.fashionsdk.configuration.features.picker.history.strings.AiutaImagePickerUploadsHistoryFeatureStrings
import com.aiuta.fashionsdk.configuration.features.picker.history.styles.AiutaImagePickerUploadsHistoryFeatureStyles
import com.aiuta.fashionsdk.configuration.internal.utils.checkNotNullWithDescription

public class AiutaImagePickerUploadsHistoryFeature(
    public val strings: AiutaImagePickerUploadsHistoryFeatureStrings,
    public val styles: AiutaImagePickerUploadsHistoryFeatureStyles,
    public val dataProvider: AiutaImagePickerUploadsHistoryFeatureDataProvider?,
) : AiutaFeature {
    public class Builder : AiutaFeature.Builder {
        public var strings: AiutaImagePickerUploadsHistoryFeatureStrings? = null
        public var styles: AiutaImagePickerUploadsHistoryFeatureStyles? = null
        public var dataProvider: AiutaImagePickerUploadsHistoryFeatureDataProvider? = null

        public override fun build(): AiutaImagePickerUploadsHistoryFeature {
            val parentClass = "AiutaImagePickerUploadsHistoryFeature"

            return AiutaImagePickerUploadsHistoryFeature(
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

public inline fun AiutaImagePickerFeature.Builder.uploadsHistory(
    block: AiutaImagePickerUploadsHistoryFeature.Builder.() -> Unit,
): AiutaImagePickerFeature.Builder = apply {
    uploadsHistory = AiutaImagePickerUploadsHistoryFeature.Builder().apply(block).build()
}
