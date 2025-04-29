package com.aiuta.fashionsdk.configuration.ui.theme.powerby

import com.aiuta.fashionsdk.configuration.features.AiutaFeature
import com.aiuta.fashionsdk.configuration.internal.utils.checkNotNullWithDescription
import com.aiuta.fashionsdk.configuration.ui.theme.AiutaTheme
import com.aiuta.fashionsdk.configuration.ui.theme.powerby.colors.AiutaPowerBarThemeColors
import com.aiuta.fashionsdk.configuration.ui.theme.powerby.strings.AiutaPowerBarThemeStrings

public class AiutaPowerBarTheme private constructor(
    public val strings: AiutaPowerBarThemeStrings,
    public val colors: AiutaPowerBarThemeColors,
) : AiutaFeature {

    public class Builder : AiutaFeature.Builder {
        public var strings: AiutaPowerBarThemeStrings? = null
        public var colors: AiutaPowerBarThemeColors? = null

        public override fun build(): AiutaPowerBarTheme {
            val parentClass = "AiutaPowerBarTheme"

            return AiutaPowerBarTheme(
                strings = strings.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "strings",
                ),
                colors = colors.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "colors",
                ),
            )
        }
    }
}

public inline fun AiutaTheme.Builder.poweredBar(
    block: AiutaPowerBarTheme.Builder.() -> Unit,
): AiutaTheme.Builder = apply {
    powerBar = AiutaPowerBarTheme.Builder().apply(block).build()
}
