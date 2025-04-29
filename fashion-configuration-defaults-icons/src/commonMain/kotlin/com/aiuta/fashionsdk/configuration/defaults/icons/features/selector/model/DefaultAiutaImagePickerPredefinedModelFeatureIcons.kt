package com.aiuta.fashionsdk.configuration.defaults.icons.features.selector.model

import com.aiuta.fashion_configuration_defaults_icons.generated.resources.Res
import com.aiuta.fashion_configuration_defaults_icons.generated.resources.ic_select_model_24
import com.aiuta.fashionsdk.compose.resources.drawable.AiutaComposeDrawableResource
import com.aiuta.fashionsdk.compose.resources.drawable.AiutaIcon
import com.aiuta.fashionsdk.configuration.features.picker.model.icons.AiutaImagePickerPredefinedModelFeatureIcons

public class DefaultAiutaImagePickerPredefinedModelFeatureIcons : AiutaImagePickerPredefinedModelFeatureIcons {
    override val selectModels24: AiutaIcon = AiutaIcon(
        iconResource = AiutaComposeDrawableResource(Res.drawable.ic_select_model_24),
    )
}
