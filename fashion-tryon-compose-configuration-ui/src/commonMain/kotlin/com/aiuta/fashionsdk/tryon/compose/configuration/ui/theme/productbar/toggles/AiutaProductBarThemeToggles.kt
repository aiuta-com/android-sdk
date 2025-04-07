package com.aiuta.fashionsdk.tryon.compose.configuration.ui.theme.productbar.toggles

public interface AiutaProductBarThemeToggles {
    public val isProductFistImageExtendedPaddingApplied: Boolean

    public class Default : AiutaProductBarThemeToggles {
        override val isProductFistImageExtendedPaddingApplied: Boolean = false
    }
}
