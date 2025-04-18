package com.aiuta.fashionsdk.tryon.compose.defaults.icons.features.tryon.feedback

import com.aiuta.fashion_tryon_compose_defaults_icons.generated.resources.Res
import com.aiuta.fashion_tryon_compose_defaults_icons.generated.resources.ic_dislike_36
import com.aiuta.fashion_tryon_compose_defaults_icons.generated.resources.ic_gratitude_40
import com.aiuta.fashion_tryon_compose_defaults_icons.generated.resources.ic_like_36
import com.aiuta.fashionsdk.tryon.compose.configuration.features.tryon.feedback.icons.AiutaTryOnFeedbackFeatureIcons
import com.aiuta.fashionsdk.tryon.compose.resources.drawable.AiutaComposeDrawableResource
import com.aiuta.fashionsdk.tryon.compose.resources.drawable.AiutaIcon

public class DefaultAiutaTryOnFeedbackFeatureIcons : AiutaTryOnFeedbackFeatureIcons {
    override val like36: AiutaIcon = AiutaIcon(
        iconResource = AiutaComposeDrawableResource(Res.drawable.ic_like_36),
    )
    override val dislike36: AiutaIcon = AiutaIcon(
        iconResource = AiutaComposeDrawableResource(Res.drawable.ic_dislike_36),
    )
    override val gratitude40: AiutaIcon = AiutaIcon(
        iconResource = AiutaComposeDrawableResource(Res.drawable.ic_gratitude_40),
    )
}
