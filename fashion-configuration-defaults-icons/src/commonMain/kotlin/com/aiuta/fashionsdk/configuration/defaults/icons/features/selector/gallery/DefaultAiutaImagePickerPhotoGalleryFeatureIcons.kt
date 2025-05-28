package com.aiuta.fashionsdk.configuration.defaults.icons.features.selector.gallery

import com.aiuta.fashion_configuration_defaults_icons.generated.resources.Res
import com.aiuta.fashion_configuration_defaults_icons.generated.resources.ic_gallery_24
import com.aiuta.fashionsdk.compose.resources.drawable.AiutaComposeDrawableResource
import com.aiuta.fashionsdk.compose.resources.drawable.AiutaIcon
import com.aiuta.fashionsdk.configuration.features.picker.gallery.icons.AiutaImagePickerPhotoGalleryFeatureIcons

/**
 * Default implementation of [AiutaImagePickerPhotoGalleryFeatureIcons].
 *
 * This class provides the default icon resources for the photo gallery feature
 * in the image picker, including the gallery icon.
 *
 * @property gallery24 24x24 pixel icon for accessing the photo gallery
 */
public class DefaultAiutaImagePickerPhotoGalleryFeatureIcons : AiutaImagePickerPhotoGalleryFeatureIcons {
    override val gallery24: AiutaIcon = AiutaIcon(
        iconResource = AiutaComposeDrawableResource(Res.drawable.ic_gallery_24),
    )
}
