package com.aiuta.fashionsdk.tryon.compose.defaults.theme.label

import com.aiuta.fashionsdk.tryon.compose.configuration.ui.theme.AiutaTheme
import com.aiuta.fashionsdk.tryon.compose.configuration.ui.theme.label.label
import com.aiuta.fashionsdk.tryon.compose.configuration.ui.theme.label.typography.AiutaLabelThemeTypography

public fun AiutaTheme.Builder.defaultLabel() {
    label {
        typography = AiutaLabelThemeTypography.Default()
    }
}
