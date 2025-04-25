package com.aiuta.fashionsdk.tryon.compose.defaults.icons.features.selector.camera

import com.aiuta.fashion_tryon_compose_defaults_icons.generated.resources.Res
import com.aiuta.fashion_tryon_compose_defaults_icons.generated.resources.ic_camera_24
import com.aiuta.fashionsdk.configuration.features.selector.camera.icons.AiutaImageSelectorCameraFeatureIcons
import com.aiuta.fashionsdk.tryon.compose.resources.drawable.AiutaComposeDrawableResource
import com.aiuta.fashionsdk.tryon.compose.resources.drawable.AiutaIcon

public class DefaultAiutaImageSelectorCameraFeatureIcons : AiutaImageSelectorCameraFeatureIcons {
    override val camera24: AiutaIcon = AiutaIcon(
        iconResource = AiutaComposeDrawableResource(Res.drawable.ic_camera_24),
    )
}
