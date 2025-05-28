package com.aiuta.fashionsdk.configuration.defaults.icons.theme.pagebar

import com.aiuta.fashion_configuration_defaults_icons.generated.resources.Res
import com.aiuta.fashion_configuration_defaults_icons.generated.resources.ic_back_24
import com.aiuta.fashion_configuration_defaults_icons.generated.resources.ic_close_24
import com.aiuta.fashionsdk.compose.resources.drawable.AiutaComposeDrawableResource
import com.aiuta.fashionsdk.compose.resources.drawable.AiutaIcon
import com.aiuta.fashionsdk.configuration.ui.theme.pagebar.icons.AiutaPageBarThemeIcons

/**
 * Default implementation of [AiutaPageBarThemeIcons].
 *
 * This class provides the default icon resources for the page bar theme,
 * including navigation and action icons used in the page header.
 *
 * @property back24 24x24 pixel back navigation icon for returning to the previous screen
 * @property close24 24x24 pixel close icon for dismissing the current screen
 */
public class DefaultAiutaPageBarThemeIcons : AiutaPageBarThemeIcons {
    override val back24: AiutaIcon = AiutaIcon(
        iconResource = AiutaComposeDrawableResource(Res.drawable.ic_back_24),
    )
    override val close24: AiutaIcon = AiutaIcon(
        iconResource = AiutaComposeDrawableResource(Res.drawable.ic_close_24),
    )
}
