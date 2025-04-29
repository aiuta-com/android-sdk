package com.aiuta.fashionsdk.configuration.features.picker.camera

import com.aiuta.fashionsdk.configuration.features.AiutaFeature
import com.aiuta.fashionsdk.configuration.features.picker.AiutaImagePickerFeature
import com.aiuta.fashionsdk.configuration.features.picker.camera.icons.AiutaImagePickerCameraFeatureIcons
import com.aiuta.fashionsdk.configuration.features.picker.camera.strings.AiutaImagePickerCameraFeatureStrings
import com.aiuta.fashionsdk.configuration.internal.utils.checkNotNullWithDescription

public class AiutaImagePickerCameraFeature(
    public val icons: AiutaImagePickerCameraFeatureIcons,
    public val strings: AiutaImagePickerCameraFeatureStrings,
) : AiutaFeature {

    public class Builder : AiutaFeature.Builder {
        public var icons: AiutaImagePickerCameraFeatureIcons? = null
        public var strings: AiutaImagePickerCameraFeatureStrings? = null

        public override fun build(): AiutaImagePickerCameraFeature {
            val parentClass = "AiutaImagePickerCameraFeature"

            return AiutaImagePickerCameraFeature(
                icons = icons.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "icons",
                ),
                strings = strings.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "strings",
                ),
            )
        }
    }
}

public inline fun AiutaImagePickerFeature.Builder.camera(
    block: AiutaImagePickerCameraFeature.Builder.() -> Unit,
): AiutaImagePickerFeature.Builder = apply {
    camera = AiutaImagePickerCameraFeature.Builder().apply(block).build()
}
