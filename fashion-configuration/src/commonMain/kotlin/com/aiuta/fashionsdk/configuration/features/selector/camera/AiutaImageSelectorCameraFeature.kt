package com.aiuta.fashionsdk.configuration.features.selector.camera

import com.aiuta.fashionsdk.configuration.features.AiutaFeature
import com.aiuta.fashionsdk.configuration.features.selector.AiutaImageSelectorFeature
import com.aiuta.fashionsdk.configuration.features.selector.camera.icons.AiutaImageSelectorCameraFeatureIcons
import com.aiuta.fashionsdk.configuration.features.selector.camera.strings.AiutaImageSelectorCameraFeatureStrings
import com.aiuta.fashionsdk.configuration.internal.utils.checkNotNullWithDescription

public class AiutaImageSelectorCameraFeature private constructor(
    public val icons: AiutaImageSelectorCameraFeatureIcons,
    public val strings: AiutaImageSelectorCameraFeatureStrings,
) : AiutaFeature {

    public class Builder : AiutaFeature.Builder {
        public var icons: AiutaImageSelectorCameraFeatureIcons? = null
        public var strings: AiutaImageSelectorCameraFeatureStrings? = null

        public override fun build(): AiutaImageSelectorCameraFeature {
            val parentClass = "AiutaImageSelectorCameraFeature"

            return AiutaImageSelectorCameraFeature(
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

public inline fun AiutaImageSelectorFeature.Builder.camera(
    block: AiutaImageSelectorCameraFeature.Builder.() -> Unit,
): AiutaImageSelectorFeature.Builder = apply {
    camera = AiutaImageSelectorCameraFeature.Builder().apply(block).build()
}
