package com.aiuta.fashionsdk.configuration.defaults.icons.features.selector.camera

import com.aiuta.fashion_configuration_defaults_icons.generated.resources.Res
import com.aiuta.fashion_configuration_defaults_icons.generated.resources.ic_camera_24
import com.aiuta.fashionsdk.compose.resources.drawable.AiutaComposeDrawableResource
import com.aiuta.fashionsdk.compose.resources.drawable.AiutaIcon
import com.aiuta.fashionsdk.configuration.features.selector.camera.icons.AiutaImageSelectorCameraFeatureIcons

public class DefaultAiutaImageSelectorCameraFeatureIcons : AiutaImageSelectorCameraFeatureIcons {
    override val camera24: AiutaIcon = AiutaIcon(
        iconResource = AiutaComposeDrawableResource(Res.drawable.ic_camera_24),
    )
}
