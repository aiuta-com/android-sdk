package com.aiuta.fashionsdk.configuration.defaults.theme.label

import com.aiuta.fashionsdk.configuration.ui.theme.AiutaTheme
import com.aiuta.fashionsdk.configuration.ui.theme.label.label
import com.aiuta.fashionsdk.configuration.ui.theme.label.typography.AiutaLabelThemeTypography

public fun AiutaTheme.Builder.defaultLabel() {
    label {
        typography = AiutaLabelThemeTypography.Default()
    }
}
