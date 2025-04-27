package com.aiuta.fashionsdk.configuration.defaults.icons.theme.productbar

import com.aiuta.fashion_configuration_defaults_icons.generated.resources.Res
import com.aiuta.fashion_configuration_defaults_icons.generated.resources.ic_arrow_16
import com.aiuta.fashionsdk.compose.resources.drawable.AiutaComposeDrawableResource
import com.aiuta.fashionsdk.compose.resources.drawable.AiutaIcon
import com.aiuta.fashionsdk.configuration.ui.theme.productbar.icons.AiutaProductBarThemeIcons

public class DefaultAiutaProductBarThemeIcons : AiutaProductBarThemeIcons {
    override val arrow16: AiutaIcon = AiutaIcon(
        iconResource = AiutaComposeDrawableResource(Res.drawable.ic_arrow_16),
    )
}
