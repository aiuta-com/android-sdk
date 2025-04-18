package com.aiuta.fashionsdk.tryon.compose.defaults.icons.features.selector.gallery

import com.aiuta.fashion_tryon_compose_defaults_icons.generated.resources.Res
import com.aiuta.fashion_tryon_compose_defaults_icons.generated.resources.ic_gallery_24
import com.aiuta.fashionsdk.tryon.compose.configuration.features.selector.gallery.icons.AiutaImageSelectorPhotoGalleryFeatureIcons
import com.aiuta.fashionsdk.tryon.compose.resources.drawable.AiutaComposeDrawableResource
import com.aiuta.fashionsdk.tryon.compose.resources.drawable.AiutaIcon

public class DefaultAiutaImageSelectorPhotoGalleryFeatureIcons : AiutaImageSelectorPhotoGalleryFeatureIcons {
    override val gallery24: AiutaIcon = AiutaIcon(
        iconResource = AiutaComposeDrawableResource(Res.drawable.ic_gallery_24),
    )
}
