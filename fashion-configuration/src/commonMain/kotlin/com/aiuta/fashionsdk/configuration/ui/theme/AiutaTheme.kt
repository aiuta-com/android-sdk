package com.aiuta.fashionsdk.configuration.ui.theme

import androidx.compose.runtime.Immutable
import com.aiuta.fashionsdk.configuration.internal.utils.checkNotNullWithDescription
import com.aiuta.fashionsdk.configuration.ui.AiutaUserInterfaceConfiguration
import com.aiuta.fashionsdk.configuration.ui.theme.button.AiutaButtonTheme
import com.aiuta.fashionsdk.configuration.ui.theme.color.AiutaColorTheme
import com.aiuta.fashionsdk.configuration.ui.theme.error.AiutaErrorSnackbarTheme
import com.aiuta.fashionsdk.configuration.ui.theme.image.AiutaImageTheme
import com.aiuta.fashionsdk.configuration.ui.theme.label.AiutaLabelTheme
import com.aiuta.fashionsdk.configuration.ui.theme.pagebar.AiutaPageBarTheme
import com.aiuta.fashionsdk.configuration.ui.theme.powerby.AiutaPowerBarTheme
import com.aiuta.fashionsdk.configuration.ui.theme.productbar.AiutaProductBarTheme
import com.aiuta.fashionsdk.configuration.ui.theme.selection.AiutaSelectionSnackbarTheme
import com.aiuta.fashionsdk.configuration.ui.theme.sheet.AiutaBottomSheetTheme

@Immutable
public class AiutaTheme private constructor(
    public val color: AiutaColorTheme,
    public val label: AiutaLabelTheme,
    public val image: AiutaImageTheme,
    public val button: AiutaButtonTheme,
    public val pageBar: AiutaPageBarTheme,
    public val powerBar: AiutaPowerBarTheme,
    public val bottomSheet: AiutaBottomSheetTheme,
    public val selectionSnackbar: AiutaSelectionSnackbarTheme,
    public val errorSnackbar: AiutaErrorSnackbarTheme,
    public val productBar: AiutaProductBarTheme,
) {
    public class Builder {
        public var color: AiutaColorTheme? = null
        public var label: AiutaLabelTheme? = null
        public var image: AiutaImageTheme? = null
        public var button: AiutaButtonTheme? = null
        public var pageBar: AiutaPageBarTheme? = null
        public var powerBar: AiutaPowerBarTheme? = null
        public var bottomSheet: AiutaBottomSheetTheme? = null
        public var selectionSnackbar: AiutaSelectionSnackbarTheme? = null
        public var errorSnackbar: AiutaErrorSnackbarTheme? = null
        public var productBar: AiutaProductBarTheme? = null

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
                powerBar = powerBar.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "powerBar",
                ),
                bottomSheet = bottomSheet.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "bottomSheet",
                ),
                selectionSnackbar = selectionSnackbar.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "selectionSnackbar",
                ),
                errorSnackbar = errorSnackbar.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "errorSnackbar",
                ),
                productBar = productBar.checkNotNullWithDescription(
                    parentClass = parentClass,
                    property = "productBar",
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
