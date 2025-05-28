package com.aiuta.fashionsdk.configuration.defaults.icons.theme.selection

import com.aiuta.fashion_configuration_defaults_icons.generated.resources.Res
import com.aiuta.fashion_configuration_defaults_icons.generated.resources.ic_check_20
import com.aiuta.fashion_configuration_defaults_icons.generated.resources.ic_trash_24
import com.aiuta.fashionsdk.compose.resources.drawable.AiutaComposeDrawableResource
import com.aiuta.fashionsdk.compose.resources.drawable.AiutaIcon
import com.aiuta.fashionsdk.configuration.ui.theme.selection.icons.AiutaSelectionSnackbarThemeIcons

/**
 * Default implementation of [AiutaSelectionSnackbarThemeIcons].
 *
 * This class provides the default icon resources for the selection snackbar theme,
 * including icons for deletion and confirmation actions.
 *
 * @property trash24 24x24 pixel trash icon for deletion actions
 * @property check20 20x20 pixel check icon for confirmation actions
 */
public class DefaultAiutaSelectionSnackbarThemeIcons : AiutaSelectionSnackbarThemeIcons {
    override val trash24: AiutaIcon = AiutaIcon(
        iconResource = AiutaComposeDrawableResource(Res.drawable.ic_trash_24),
    )
    override val check20: AiutaIcon = AiutaIcon(
        iconResource = AiutaComposeDrawableResource(Res.drawable.ic_check_20),
    )
}
