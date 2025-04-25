package com.aiuta.fashionsdk.configuration.defaults.icons.features.selector.gallery

import com.aiuta.fashion_configuration_defaults_icons.generated.resources.Res
import com.aiuta.fashion_configuration_defaults_icons.generated.resources.ic_gallery_24
import com.aiuta.fashionsdk.configuration.features.selector.gallery.icons.AiutaImageSelectorPhotoGalleryFeatureIcons
import com.aiuta.fashionsdk.tryon.compose.resources.drawable.AiutaComposeDrawableResource
import com.aiuta.fashionsdk.tryon.compose.resources.drawable.AiutaIcon

public class DefaultAiutaImageSelectorPhotoGalleryFeatureIcons : AiutaImageSelectorPhotoGalleryFeatureIcons {
    override val gallery24: AiutaIcon = AiutaIcon(
        iconResource = AiutaComposeDrawableResource(Res.drawable.ic_gallery_24),
    )
}
