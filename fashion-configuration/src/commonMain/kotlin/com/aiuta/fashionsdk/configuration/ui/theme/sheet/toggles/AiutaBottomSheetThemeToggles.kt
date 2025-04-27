package com.aiuta.fashionsdk.configuration.ui.theme.sheet.toggles

public interface AiutaBottomSheetThemeToggles {
    public val extendDelimitersToTheRight: Boolean
    public val extendDelimitersToTheLeft: Boolean

    public class Default : AiutaBottomSheetThemeToggles {
        override val extendDelimitersToTheRight: Boolean = false
        override val extendDelimitersToTheLeft: Boolean = false
    }
}
