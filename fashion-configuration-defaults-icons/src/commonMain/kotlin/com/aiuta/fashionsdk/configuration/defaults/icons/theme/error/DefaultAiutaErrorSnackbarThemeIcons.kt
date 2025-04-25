package com.aiuta.fashionsdk.configuration.defaults.icons.theme.error

import com.aiuta.fashion_configuration_defaults_icons.generated.resources.Res
import com.aiuta.fashion_configuration_defaults_icons.generated.resources.ic_error_36
import com.aiuta.fashionsdk.compose.resources.drawable.AiutaComposeDrawableResource
import com.aiuta.fashionsdk.compose.resources.drawable.AiutaIcon
import com.aiuta.fashionsdk.configuration.ui.theme.error.icons.AiutaErrorSnackbarThemeIcons

public class DefaultAiutaErrorSnackbarThemeIcons : AiutaErrorSnackbarThemeIcons {
    override val error36: AiutaIcon = AiutaIcon(
        iconResource = AiutaComposeDrawableResource(Res.drawable.ic_error_36),
    )
}
