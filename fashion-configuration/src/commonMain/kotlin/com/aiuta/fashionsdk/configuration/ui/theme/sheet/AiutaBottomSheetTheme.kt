package com.aiuta.fashionsdk.configuration.ui.theme.sheet

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.configuration.internal.utils.checkNotNullWithDescription
import com.aiuta.fashionsdk.configuration.ui.theme.AiutaTheme
import com.aiuta.fashionsdk.configuration.ui.theme.sheet.grabber.AiutaBottomSheetThemeGrabber
import com.aiuta.fashionsdk.configuration.ui.theme.sheet.shapes.AiutaBottomSheetThemeShapes
import com.aiuta.fashionsdk.configuration.ui.theme.sheet.toggles.AiutaBottomSheetThemeToggles
import com.aiuta.fashionsdk.configuration.ui.theme.sheet.typography.AiutaBottomSheetThemeTypography

@Immutable
public class AiutaBottomSheetTheme private constructor(
    public val typography: AiutaBottomSheetThemeTypography,
    public val shapes: AiutaBottomSheetThemeShapes,
    public val grabber: AiutaBottomSheetThemeGrabber,
    public val toggles: AiutaBottomSheetThemeToggles,
) {
    public class Builder {
        public var typography: AiutaBottomSheetThemeTypography? = null
        public var shapes: AiutaBottomSheetThemeShapes? = null
        public var grabber: AiutaBottomSheetThemeGrabber? = null
        public var toggles: AiutaBottomSheetThemeToggles? = null

        public fun build(): AiutaBottomSheetTheme {
            val parentClass = "AiutaBottomSheetTheme"

            return AiutaBottomSheetTheme(
                typography = typography.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "typography",
                ),
                shapes = shapes.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "shapes",
                ),
                grabber = grabber.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "grabber",
                ),
                toggles = toggles.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "toggles",
                ),
            )
        }
    }
}

public inline fun AiutaTheme.Builder.bottomSheet(
    block: AiutaBottomSheetTheme.Builder.() -> Unit,
): AiutaTheme.Builder = apply {
    bottomSheet = AiutaBottomSheetTheme.Builder().apply(block).build()
}
