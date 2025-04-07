package com.aiuta.fashionsdk.tryon.compose.defaults.icons.theme.pagebar

import com.aiuta.fashion_tryon_compose_defaults_icons.generated.resources.Res
import com.aiuta.fashion_tryon_compose_defaults_icons.generated.resources.ic_back_24
import com.aiuta.fashion_tryon_compose_defaults_icons.generated.resources.ic_close_24
import com.aiuta.fashionsdk.tryon.compose.configuration.ui.resources.AiutaComposeDrawableResource
import com.aiuta.fashionsdk.tryon.compose.configuration.ui.resources.AiutaIcon
import com.aiuta.fashionsdk.tryon.compose.configuration.ui.theme.pagebar.icons.AiutaPageBarThemeIcons

public class DefaultAiutaPageBarThemeIcons : AiutaPageBarThemeIcons {
    override val back24: AiutaIcon = AiutaIcon(
        iconResource = AiutaComposeDrawableResource(Res.drawable.ic_back_24),
    )
    override val close24: AiutaIcon = AiutaIcon(
        iconResource = AiutaComposeDrawableResource(Res.drawable.ic_close_24),
    )
}
