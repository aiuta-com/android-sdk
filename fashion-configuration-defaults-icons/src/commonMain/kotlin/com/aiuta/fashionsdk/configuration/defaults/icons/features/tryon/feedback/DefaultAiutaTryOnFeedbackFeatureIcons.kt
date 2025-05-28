package com.aiuta.fashionsdk.configuration.defaults.icons.features.tryon.feedback

import com.aiuta.fashion_configuration_defaults_icons.generated.resources.Res
import com.aiuta.fashion_configuration_defaults_icons.generated.resources.ic_dislike_36
import com.aiuta.fashion_configuration_defaults_icons.generated.resources.ic_gratitude_40
import com.aiuta.fashion_configuration_defaults_icons.generated.resources.ic_like_36
import com.aiuta.fashionsdk.compose.resources.drawable.AiutaComposeDrawableResource
import com.aiuta.fashionsdk.compose.resources.drawable.AiutaIcon
import com.aiuta.fashionsdk.configuration.features.tryon.feedback.icons.AiutaTryOnFeedbackFeatureIcons

/**
 * Default implementation of [AiutaTryOnFeedbackFeatureIcons].
 *
 * This class provides the default icon resources for the try-on feedback feature,
 * including like, dislike, and gratitude icons.
 *
 * @property like36 36x36 pixel icon for positive feedback
 * @property dislike36 36x36 pixel icon for negative feedback
 * @property gratitude40 40x40 pixel icon for expressing gratitude
 */
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
