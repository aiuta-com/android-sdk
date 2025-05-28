package com.aiuta.fashionsdk.configuration.defaults.icons.theme.error

import com.aiuta.fashion_configuration_defaults_icons.generated.resources.Res
import com.aiuta.fashion_configuration_defaults_icons.generated.resources.ic_error_36
import com.aiuta.fashionsdk.compose.resources.drawable.AiutaComposeDrawableResource
import com.aiuta.fashionsdk.compose.resources.drawable.AiutaIcon
import com.aiuta.fashionsdk.configuration.ui.theme.error.icons.AiutaErrorSnackbarThemeIcons

/**
 * Default implementation of [AiutaErrorSnackbarThemeIcons].
 *
 * This class provides the default icon resources for the error snackbar theme,
 * including the error icon used to indicate error states.
 *
 * @property error36 36x36 pixel error icon for displaying error states
 */
public class DefaultAiutaErrorSnackbarThemeIcons : AiutaErrorSnackbarThemeIcons {
    override val error36: AiutaIcon = AiutaIcon(
        iconResource = AiutaComposeDrawableResource(Res.drawable.ic_error_36),
    )
}
