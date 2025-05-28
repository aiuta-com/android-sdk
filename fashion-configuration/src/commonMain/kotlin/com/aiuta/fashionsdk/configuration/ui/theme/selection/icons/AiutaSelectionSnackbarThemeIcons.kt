package com.aiuta.fashionsdk.configuration.ui.theme.selection.icons

import com.aiuta.fashionsdk.compose.resources.drawable.AiutaIcon

/**
 * Icon configuration for selection snackbar.
 * 
 * This interface defines the icons used in selection-related snackbar
 * throughout the SDK. It provides consistent selection iconography
 * that can be customized to match your app's design system.
 * 
 * 
 * @property trash24 Icon for delete/remove actions (24dp)
 * @property check20 Icon for selection confirmation (20dp)
 */
public interface AiutaSelectionSnackbarThemeIcons {
    public val trash24: AiutaIcon
    public val check20: AiutaIcon
}
