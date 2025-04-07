package com.aiuta.fashionsdk.tryon.compose.defaults.icons.theme.image

import com.aiuta.fashion_tryon_compose_defaults_icons.generated.resources.Res
import com.aiuta.fashion_tryon_compose_defaults_icons.generated.resources.ic_error_36
import com.aiuta.fashion_tryon_compose_defaults_icons.generated.resources.ic_image_error_36
import com.aiuta.fashionsdk.tryon.compose.configuration.ui.resources.AiutaComposeDrawableResource
import com.aiuta.fashionsdk.tryon.compose.configuration.ui.resources.AiutaIcon
import com.aiuta.fashionsdk.tryon.compose.configuration.ui.theme.image.icons.AiutaImageThemeIcons

public class DefaultAiutaImageThemeIcons: AiutaImageThemeIcons {
    override val error36: AiutaIcon = AiutaIcon(
        iconResource = AiutaComposeDrawableResource(Res.drawable.ic_image_error_36)
    )
}
