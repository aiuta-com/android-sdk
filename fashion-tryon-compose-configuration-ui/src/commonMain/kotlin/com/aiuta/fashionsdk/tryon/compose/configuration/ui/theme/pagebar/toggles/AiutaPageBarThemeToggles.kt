package com.aiuta.fashionsdk.tryon.compose.configuration.ui.theme.pagebar.toggles

public interface AiutaPageBarThemeToggles {
    public val preferCloseButtonOnTheRight: Boolean

    public class Default : AiutaPageBarThemeToggles {
        override val preferCloseButtonOnTheRight: Boolean = false
    }
}
