package com.aiuta.fashionsdk.tryon.compose.configuration.ui.theme.productbar.toggles

public interface AiutaProductBarThemeToggles {
    public val applyProductFirstImageExtraPadding: Boolean

    public class Default : AiutaProductBarThemeToggles {
        override val applyProductFirstImageExtraPadding: Boolean = false
    }
}
