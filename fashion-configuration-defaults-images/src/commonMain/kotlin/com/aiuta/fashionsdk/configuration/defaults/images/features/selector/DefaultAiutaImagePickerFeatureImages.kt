package com.aiuta.fashionsdk.configuration.defaults.images.features.selector

import com.aiuta.fashion_configuration_defaults_images.generated.resources.Res
import com.aiuta.fashion_configuration_defaults_images.generated.resources.onboarding_good_image_1
import com.aiuta.fashion_configuration_defaults_images.generated.resources.selector_empty_small_image_1
import com.aiuta.fashionsdk.compose.resources.drawable.AiutaComposeDrawableResource
import com.aiuta.fashionsdk.compose.resources.drawable.AiutaDrawableResource
import com.aiuta.fashionsdk.configuration.features.picker.images.AiutaImagePickerFeatureImages

public class DefaultAiutaImagePickerFeatureImages : AiutaImagePickerFeatureImages {
    override val examples: List<AiutaDrawableResource> = listOf(
        AiutaComposeDrawableResource(Res.drawable.selector_empty_small_image_1),
        AiutaComposeDrawableResource(Res.drawable.onboarding_good_image_1),
    )
}
