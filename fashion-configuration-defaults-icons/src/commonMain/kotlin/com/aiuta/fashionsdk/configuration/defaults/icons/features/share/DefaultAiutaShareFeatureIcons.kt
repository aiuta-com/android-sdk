package com.aiuta.fashionsdk.configuration.defaults.icons.features.share

import com.aiuta.fashion_configuration_defaults_icons.generated.resources.Res
import com.aiuta.fashion_configuration_defaults_icons.generated.resources.ic_share_24
import com.aiuta.fashionsdk.compose.resources.drawable.AiutaComposeDrawableResource
import com.aiuta.fashionsdk.compose.resources.drawable.AiutaIcon
import com.aiuta.fashionsdk.configuration.features.share.icons.AiutaShareFeatureIcons

public class DefaultAiutaShareFeatureIcons : AiutaShareFeatureIcons {
    override val share24: AiutaIcon = AiutaIcon(
        iconResource = AiutaComposeDrawableResource(Res.drawable.ic_share_24),
    )
}
