package com.aiuta.fashionsdk.configuration.ui.theme.image

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.configuration.internal.utils.checkNotNullWithDescription
import com.aiuta.fashionsdk.configuration.ui.theme.AiutaTheme
import com.aiuta.fashionsdk.configuration.ui.theme.image.icons.AiutaImageThemeIcons
import com.aiuta.fashionsdk.configuration.ui.theme.image.shapes.AiutaImageThemeShapes

@Immutable
public class AiutaImageTheme private constructor(
    public val shapes: AiutaImageThemeShapes,
    public val icons: AiutaImageThemeIcons,
) {
    public class Builder {
        public var shapes: AiutaImageThemeShapes? = null
        public var icons: AiutaImageThemeIcons? = null

        public fun build(): AiutaImageTheme {
            val parentClass = "AiutaImageTheme"

            return AiutaImageTheme(
                shapes = shapes.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "shapes",
                ),
                icons = icons.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "icons",
                ),
            )
        }
    }
}

public inline fun AiutaTheme.Builder.image(
    block: AiutaImageTheme.Builder.() -> Unit,
): AiutaTheme.Builder = apply {
    image = AiutaImageTheme.Builder().apply(block).build()
}
