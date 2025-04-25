package com.aiuta.fashionsdk.tryon.compose.defaults.images.features.selector

import com.aiuta.fashion_tryon_compose_defaults_images.generated.resources.Res
import com.aiuta.fashion_tryon_compose_defaults_images.generated.resources.onboarding_good_image_1
import com.aiuta.fashion_tryon_compose_defaults_images.generated.resources.selector_empty_small_image_1
import com.aiuta.fashionsdk.configuration.features.features.selector.images.AiutaImageSelectorFeatureImages
import com.aiuta.fashionsdk.tryon.compose.resources.drawable.AiutaComposeDrawableResource
import com.aiuta.fashionsdk.tryon.compose.resources.drawable.AiutaDrawableResource

public class DefaultAiutaImageSelectorFeatureImages : AiutaImageSelectorFeatureImages {
    override val examples: List<AiutaDrawableResource> = listOf(
        AiutaComposeDrawableResource(Res.drawable.selector_empty_small_image_1),
        AiutaComposeDrawableResource(Res.drawable.onboarding_good_image_1),
    )
}
