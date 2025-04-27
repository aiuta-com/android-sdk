package com.aiuta.fashionsdk.configuration.defaults.icons.theme.selection

import com.aiuta.fashion_configuration_defaults_icons.generated.resources.Res
import com.aiuta.fashion_configuration_defaults_icons.generated.resources.ic_check_20
import com.aiuta.fashion_configuration_defaults_icons.generated.resources.ic_trash_24
import com.aiuta.fashionsdk.compose.resources.drawable.AiutaComposeDrawableResource
import com.aiuta.fashionsdk.compose.resources.drawable.AiutaIcon
import com.aiuta.fashionsdk.configuration.ui.theme.selection.icons.AiutaSelectionSnackbarThemeIcons

public class DefaultAiutaSelectionSnackbarThemeIcons : AiutaSelectionSnackbarThemeIcons {
    override val trash24: AiutaIcon = AiutaIcon(
        iconResource = AiutaComposeDrawableResource(Res.drawable.ic_trash_24),
    )
    override val check20: AiutaIcon = AiutaIcon(
        iconResource = AiutaComposeDrawableResource(Res.drawable.ic_check_20),
    )
}
