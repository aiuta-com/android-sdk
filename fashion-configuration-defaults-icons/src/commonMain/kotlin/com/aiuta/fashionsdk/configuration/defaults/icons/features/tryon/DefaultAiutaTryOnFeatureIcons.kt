package com.aiuta.fashionsdk.configuration.defaults.icons.features.tryon

import com.aiuta.fashion_configuration_defaults_icons.generated.resources.Res
import com.aiuta.fashion_configuration_defaults_icons.generated.resources.ic_arrow_16
import com.aiuta.fashion_configuration_defaults_icons.generated.resources.ic_magic_20
import com.aiuta.fashionsdk.configuration.features.tryon.icons.AiutaTryOnFeatureIcons
import com.aiuta.fashionsdk.tryon.compose.resources.drawable.AiutaComposeDrawableResource
import com.aiuta.fashionsdk.tryon.compose.resources.drawable.AiutaIcon

public class DefaultAiutaTryOnFeatureIcons : AiutaTryOnFeatureIcons {
    override val arrow16: AiutaIcon = AiutaIcon(
        iconResource = AiutaComposeDrawableResource(Res.drawable.ic_arrow_16),
    )
    override val magic20: AiutaIcon = AiutaIcon(
        iconResource = AiutaComposeDrawableResource(Res.drawable.ic_magic_20),
    )
}
