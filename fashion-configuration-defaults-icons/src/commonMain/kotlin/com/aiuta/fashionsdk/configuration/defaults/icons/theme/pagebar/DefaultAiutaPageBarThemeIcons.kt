package com.aiuta.fashionsdk.configuration.defaults.icons.theme.pagebar

import com.aiuta.fashion_configuration_defaults_icons.generated.resources.Res
import com.aiuta.fashion_configuration_defaults_icons.generated.resources.ic_back_24
import com.aiuta.fashion_configuration_defaults_icons.generated.resources.ic_close_24
import com.aiuta.fashionsdk.compose.resources.drawable.AiutaComposeDrawableResource
import com.aiuta.fashionsdk.compose.resources.drawable.AiutaIcon
import com.aiuta.fashionsdk.configuration.ui.theme.pagebar.icons.AiutaPageBarThemeIcons

public class DefaultAiutaPageBarThemeIcons : AiutaPageBarThemeIcons {
    override val back24: AiutaIcon = AiutaIcon(
        iconResource = AiutaComposeDrawableResource(Res.drawable.ic_back_24),
    )
    override val close24: AiutaIcon = AiutaIcon(
        iconResource = AiutaComposeDrawableResource(Res.drawable.ic_close_24),
    )
}
