package com.aiuta.fashionsdk.configuration.ui.theme.powerby.colors

public interface AiutaPowerBarThemeColors {
    public val aiuta: AiutaPowerBarColorScheme

    public class Default : AiutaPowerBarThemeColors {
        override val aiuta: AiutaPowerBarColorScheme = AiutaPowerBarColorScheme.DEFAULT
    }
}
