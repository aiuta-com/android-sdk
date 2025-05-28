package com.aiuta.fashionsdk.configuration.defaults.icons.theme.image

import com.aiuta.fashion_configuration_defaults_icons.generated.resources.Res
import com.aiuta.fashion_configuration_defaults_icons.generated.resources.ic_image_error_36
import com.aiuta.fashionsdk.compose.resources.drawable.AiutaComposeDrawableResource
import com.aiuta.fashionsdk.compose.resources.drawable.AiutaIcon
import com.aiuta.fashionsdk.configuration.ui.theme.image.icons.AiutaImageThemeIcons

/**
 * Default implementation of [AiutaImageThemeIcons].
 *
 * This class provides the default icon resources for the image theme,
 * including the error icon used to indicate image loading or display errors.
 *
 * @property error36 36x36 pixel error icon for displaying image-related error states
 */
public class DefaultAiutaImageThemeIcons : AiutaImageThemeIcons {
    override val error36: AiutaIcon = AiutaIcon(
        iconResource = AiutaComposeDrawableResource(Res.drawable.ic_image_error_36),
    )
}
