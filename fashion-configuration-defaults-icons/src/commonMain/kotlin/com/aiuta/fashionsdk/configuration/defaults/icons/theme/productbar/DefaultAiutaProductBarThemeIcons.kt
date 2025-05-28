package com.aiuta.fashionsdk.configuration.defaults.icons.theme.productbar

import com.aiuta.fashion_configuration_defaults_icons.generated.resources.Res
import com.aiuta.fashion_configuration_defaults_icons.generated.resources.ic_arrow_16
import com.aiuta.fashionsdk.compose.resources.drawable.AiutaComposeDrawableResource
import com.aiuta.fashionsdk.compose.resources.drawable.AiutaIcon
import com.aiuta.fashionsdk.configuration.ui.theme.productbar.icons.AiutaProductBarThemeIcons

/**
 * Default implementation of [AiutaProductBarThemeIcons].
 *
 * This class provides the default icon resources for the product bar theme,
 * including the arrow icon used for navigation.
 *
 * @property arrow16 16x16 pixel arrow icon for navigation in the product bar
 */
public class DefaultAiutaProductBarThemeIcons : AiutaProductBarThemeIcons {
    override val arrow16: AiutaIcon = AiutaIcon(
        iconResource = AiutaComposeDrawableResource(Res.drawable.ic_arrow_16),
    )
}
