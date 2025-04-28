package com.aiuta.fashionsdk.configuration.defaults.theme.powerby

import com.aiuta.fashionsdk.configuration.ui.theme.AiutaTheme
import com.aiuta.fashionsdk.configuration.ui.theme.powerby.colors.AiutaPowerBarThemeColors
import com.aiuta.fashionsdk.configuration.ui.theme.powerby.poweredBar
import com.aiuta.fashionsdk.configuration.ui.theme.powerby.strings.AiutaPowerBarThemeStrings

public fun AiutaTheme.Builder.defaultPoweredBar(): AiutaTheme.Builder = poweredBar {
    strings = AiutaPowerBarThemeStrings.Default()
    colors = AiutaPowerBarThemeColors.Default()
}
