package com.aiuta.fashionsdk.configuration.defaults.icons.features.tryon.history

import com.aiuta.fashion_configuration_defaults_icons.generated.resources.Res
import com.aiuta.fashion_configuration_defaults_icons.generated.resources.ic_history_24
import com.aiuta.fashionsdk.compose.resources.drawable.AiutaComposeDrawableResource
import com.aiuta.fashionsdk.compose.resources.drawable.AiutaIcon
import com.aiuta.fashionsdk.configuration.features.tryon.history.icons.AiutaTryOnGenerationsHistoryFeatureIcons

/**
 * Default implementation of [AiutaTryOnGenerationsHistoryFeatureIcons].
 *
 * This class provides the default icon resources for the try-on generations history feature,
 * including the history icon.
 *
 * @property history24 24x24 pixel icon for accessing the try-on history
 */
public class DefaultAiutaTryOnGenerationsHistoryFeatureIcons : AiutaTryOnGenerationsHistoryFeatureIcons {
    override val history24: AiutaIcon = AiutaIcon(
        iconResource = AiutaComposeDrawableResource(Res.drawable.ic_history_24),
    )
}
