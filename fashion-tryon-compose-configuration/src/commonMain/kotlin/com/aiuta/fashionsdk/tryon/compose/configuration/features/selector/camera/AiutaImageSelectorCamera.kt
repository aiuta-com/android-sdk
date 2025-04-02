package com.aiuta.fashionsdk.tryon.compose.configuration.features.selector.camera

import com.aiuta.fashionsdk.tryon.compose.configuration.features.AiutaFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.selector.AiutaImageSelectorFeature
import com.aiuta.fashionsdk.tryon.compose.configuration.features.selector.camera.icons.AiutaImageSelectorCameraIcons
import com.aiuta.fashionsdk.tryon.compose.configuration.features.selector.camera.strings.AiutaImageSelectorCameraStrings
import com.aiuta.fashionsdk.tryon.compose.configuration.utils.checkNotNullWithDescription

public class AiutaImageSelectorCamera private constructor(
    public val icons: AiutaImageSelectorCameraIcons,
    public val strings: AiutaImageSelectorCameraStrings,
) : AiutaFeature {

    public class Builder : AiutaFeature.Builder {
        public var icons: AiutaImageSelectorCameraIcons? = null
        public var strings: AiutaImageSelectorCameraStrings? = null

        public override fun build(): AiutaImageSelectorCamera {
            val parentClass = "AiutaImageSelectorCamera"

            return AiutaImageSelectorCamera(
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
    block: AiutaImageSelectorCamera.Builder.() -> Unit,
): AiutaImageSelectorFeature.Builder = apply {
    camera = AiutaImageSelectorCamera.Builder().apply(block).build()
}
