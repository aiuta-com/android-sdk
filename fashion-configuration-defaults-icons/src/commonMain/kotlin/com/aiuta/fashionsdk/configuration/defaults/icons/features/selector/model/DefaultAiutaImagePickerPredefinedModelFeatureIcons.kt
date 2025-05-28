package com.aiuta.fashionsdk.configuration.defaults.icons.features.selector.model

import com.aiuta.fashion_configuration_defaults_icons.generated.resources.Res
import com.aiuta.fashion_configuration_defaults_icons.generated.resources.ic_select_model_24
import com.aiuta.fashionsdk.compose.resources.drawable.AiutaComposeDrawableResource
import com.aiuta.fashionsdk.compose.resources.drawable.AiutaIcon
import com.aiuta.fashionsdk.configuration.features.picker.model.icons.AiutaImagePickerPredefinedModelFeatureIcons

/**
 * Default implementation of [AiutaImagePickerPredefinedModelFeatureIcons].
 *
 * This class provides the default icon resources for the predefined model selection feature
 * in the image picker, including the model selection icon.
 *
 * @property selectModels24 24x24 pixel icon for selecting predefined models
 */
public class DefaultAiutaImagePickerPredefinedModelFeatureIcons : AiutaImagePickerPredefinedModelFeatureIcons {
    override val selectModels24: AiutaIcon = AiutaIcon(
        iconResource = AiutaComposeDrawableResource(Res.drawable.ic_select_model_24),
    )
}
