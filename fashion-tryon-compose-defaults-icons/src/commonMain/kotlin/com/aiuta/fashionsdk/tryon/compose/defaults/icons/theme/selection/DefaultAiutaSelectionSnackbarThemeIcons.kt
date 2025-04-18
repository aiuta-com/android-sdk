package com.aiuta.fashionsdk.tryon.compose.defaults.icons.theme.selection

import com.aiuta.fashion_tryon_compose_defaults_icons.generated.resources.Res
import com.aiuta.fashion_tryon_compose_defaults_icons.generated.resources.ic_check_20
import com.aiuta.fashion_tryon_compose_defaults_icons.generated.resources.ic_trash_24
import com.aiuta.fashionsdk.tryon.compose.configuration.ui.theme.selection.icons.AiutaSelectionSnackbarThemeIcons
import com.aiuta.fashionsdk.tryon.compose.resources.drawable.AiutaComposeDrawableResource
import com.aiuta.fashionsdk.tryon.compose.resources.drawable.AiutaIcon

public class DefaultAiutaSelectionSnackbarThemeIcons : AiutaSelectionSnackbarThemeIcons {
    override val trash24: AiutaIcon = AiutaIcon(
        iconResource = AiutaComposeDrawableResource(Res.drawable.ic_trash_24),
    )
    override val check20: AiutaIcon = AiutaIcon(
        iconResource = AiutaComposeDrawableResource(Res.drawable.ic_check_20),
    )
}
