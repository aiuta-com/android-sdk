package com.aiuta.fashionsdk.configuration.defaults.icons.features.tryon.repicking

import com.aiuta.fashion_configuration_defaults_icons.generated.resources.Res
import com.aiuta.fashion_configuration_defaults_icons.generated.resources.ic_repicking_24
import com.aiuta.fashionsdk.compose.resources.drawable.AiutaComposeDrawableResource
import com.aiuta.fashionsdk.compose.resources.drawable.AiutaIcon
import com.aiuta.fashionsdk.configuration.features.tryon.other.icons.AiutaTryOnWithOtherPhotoFeatureIcons

/**
 * Default implementation of [AiutaTryOnWithOtherPhotoFeatureIcons].
 *
 * This class provides the default icon resources for the try-on photo repicking feature,
 * including the change photo icon.
 *
 * @property changePhoto24 24x24 pixel icon for changing the try-on photo
 */
public class DefaultAiutaTryOnWithOtherPhotoFeatureIcons : AiutaTryOnWithOtherPhotoFeatureIcons {
    override val changePhoto24: AiutaIcon = AiutaIcon(
        iconResource = AiutaComposeDrawableResource(Res.drawable.ic_repicking_24),
    )
}
