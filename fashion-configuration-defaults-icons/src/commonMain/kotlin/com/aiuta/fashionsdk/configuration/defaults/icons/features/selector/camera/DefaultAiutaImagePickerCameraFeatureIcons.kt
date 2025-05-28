package com.aiuta.fashionsdk.configuration.defaults.icons.features.selector.camera

import com.aiuta.fashion_configuration_defaults_icons.generated.resources.Res
import com.aiuta.fashion_configuration_defaults_icons.generated.resources.ic_camera_24
import com.aiuta.fashionsdk.compose.resources.drawable.AiutaComposeDrawableResource
import com.aiuta.fashionsdk.compose.resources.drawable.AiutaIcon
import com.aiuta.fashionsdk.configuration.features.picker.camera.icons.AiutaImagePickerCameraFeatureIcons

/**
 * Default implementation of [AiutaImagePickerCameraFeatureIcons].
 *
 * This class provides the default icon resources for the camera feature
 * in the image picker, including the camera icon.
 *
 * @property camera24 24x24 pixel icon for accessing the camera
 */
public class DefaultAiutaImagePickerCameraFeatureIcons : AiutaImagePickerCameraFeatureIcons {
    override val camera24: AiutaIcon = AiutaIcon(
        iconResource = AiutaComposeDrawableResource(Res.drawable.ic_camera_24),
    )
}
