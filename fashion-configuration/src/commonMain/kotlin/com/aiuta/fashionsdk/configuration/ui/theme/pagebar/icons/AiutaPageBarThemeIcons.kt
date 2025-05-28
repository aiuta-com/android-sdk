package com.aiuta.fashionsdk.configuration.ui.theme.pagebar.icons

import com.aiuta.fashionsdk.compose.resources.drawable.AiutaIcon

/**
 * Icon configuration for page bar navigation elements.
 * 
 * This interface defines icons used for navigation controls in the page bar,
 * such as back and close buttons. It provides consistent icon styling that can be
 * customized to match your app's design system.
 * 
 * 
 * @property back24 Icon displayed in the back button (24dp)
 * @property close24 Icon displayed in the close button (24dp)
 */
public interface AiutaPageBarThemeIcons {
    public val back24: AiutaIcon
    public val close24: AiutaIcon
}
