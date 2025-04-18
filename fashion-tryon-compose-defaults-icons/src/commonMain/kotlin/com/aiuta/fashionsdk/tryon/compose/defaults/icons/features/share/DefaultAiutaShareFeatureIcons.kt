package com.aiuta.fashionsdk.tryon.compose.defaults.icons.features.share

import com.aiuta.fashion_tryon_compose_defaults_icons.generated.resources.Res
import com.aiuta.fashion_tryon_compose_defaults_icons.generated.resources.ic_share_24
import com.aiuta.fashionsdk.tryon.compose.configuration.features.share.icons.AiutaShareFeatureIcons
import com.aiuta.fashionsdk.tryon.compose.resources.drawable.AiutaComposeDrawableResource
import com.aiuta.fashionsdk.tryon.compose.resources.drawable.AiutaIcon

public class DefaultAiutaShareFeatureIcons : AiutaShareFeatureIcons {
    override val share24: AiutaIcon = AiutaIcon(
        iconResource = AiutaComposeDrawableResource(Res.drawable.ic_share_24),
    )
}
