package com.aiuta.fashionsdk.tryon.compose.configuration.ui.theme

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.tryon.compose.configuration.ui.AiutaUserInterfaceConfiguration
import com.aiuta.fashionsdk.tryon.compose.configuration.ui.internal.utils.checkNotNullWithDescription
import com.aiuta.fashionsdk.tryon.compose.configuration.ui.theme.button.AiutaButtonTheme
import com.aiuta.fashionsdk.tryon.compose.configuration.ui.theme.color.AiutaColorTheme
import com.aiuta.fashionsdk.tryon.compose.configuration.ui.theme.image.AiutaImageTheme
import com.aiuta.fashionsdk.tryon.compose.configuration.ui.theme.label.AiutaLabelTheme
import com.aiuta.fashionsdk.tryon.compose.configuration.ui.theme.pagebar.AiutaPageBarTheme
import com.aiuta.fashionsdk.tryon.compose.configuration.ui.theme.sheet.AiutaBottomSheetTheme

@Immutable
public class AiutaTheme private constructor(
    public val color: AiutaColorTheme,
    public val label: AiutaLabelTheme,
    public val image: AiutaImageTheme,
    public val button: AiutaButtonTheme,
    public val pageBar: AiutaPageBarTheme,
    public val bottomSheet: AiutaBottomSheetTheme,
) {
    public class Builder {
        public var color: AiutaColorTheme? = null
        public var label: AiutaLabelTheme? = null
        public var image: AiutaImageTheme? = null
        public var button: AiutaButtonTheme? = null
        public var pageBar: AiutaPageBarTheme? = null
        public var bottomSheet: AiutaBottomSheetTheme? = null

        public fun build(): AiutaTheme {
            val parentClass = "AiutaTheme"

            return AiutaTheme(
                color = color.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "color",
                ),
                label = label.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "label",
                ),
                image = image.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "image",
                ),
                button = button.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "button",
                ),
                pageBar = pageBar.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "pageBar",
                ),
                bottomSheet = bottomSheet.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "bottomSheet",
                ),
            )
        }
    }
}

public inline fun AiutaUserInterfaceConfiguration.Builder.theme(
    block: AiutaTheme.Builder.() -> Unit,
): AiutaUserInterfaceConfiguration.Builder = apply {
    theme = AiutaTheme.Builder().apply(block).build()
}
