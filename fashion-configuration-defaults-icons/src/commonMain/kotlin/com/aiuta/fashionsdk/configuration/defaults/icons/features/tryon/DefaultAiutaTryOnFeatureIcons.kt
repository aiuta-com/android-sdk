package com.aiuta.fashionsdk.configuration.defaults.icons.features.tryon

import com.aiuta.fashion_configuration_defaults_icons.generated.resources.Res
import com.aiuta.fashion_configuration_defaults_icons.generated.resources.ic_tryon_20
import com.aiuta.fashionsdk.compose.resources.drawable.AiutaComposeDrawableResource
import com.aiuta.fashionsdk.compose.resources.drawable.AiutaIcon
import com.aiuta.fashionsdk.configuration.features.tryon.icons.AiutaTryOnFeatureIcons

/**
 * Default implementation of [AiutaTryOnFeatureIcons].
 *
 * This class provides the default icon resources for the try-on feature,
 * including the main try-on icon.
 *
 * @property tryOn20 20x20 pixel icon for the try-on feature
 */
public class DefaultAiutaTryOnFeatureIcons : AiutaTryOnFeatureIcons {
    override val tryOn20: AiutaIcon = AiutaIcon(
        iconResource = AiutaComposeDrawableResource(Res.drawable.ic_tryon_20),
    )
}
